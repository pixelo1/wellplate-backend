import os
import requests
from github import Github
import json

# GitHub 토큰 및 PR 정보 가져오기
token = os.getenv('GITHUB_TOKEN')
pr_number = os.getenv('PR_NUMBER')
repo_name = os.getenv('GITHUB_REPOSITORY')

# GitHub API 클라이언트 초기화
g = Github(token)
repo = g.get_repo(repo_name)
pr = repo.get_pull(int(pr_number))

# 변경된 파일 가져오기
changed_files = [f.filename for f in pr.get_files()]


# 코드 내용과 diff 가져오기
code_contents = ""
file_diffs = {}
for file in changed_files:
    file_path = file.filename
    contents = repo.get_contents(file_path, ref=pr.head.ref)
    code = contents.decoded_content.decode('utf-8')
    code_contents += f"\n\n### 파일: {file_path}\n\n{code}"
    file_diffs[file_path] = file.patch  # 파일의 diff 저장

# LLM API에 코드 리뷰 요청 보내기
llm_api_url = 'http://localhost:8000/llama3-2/3b/ask'  # FastAPI 서비스 주소

# LLM이 파일명, 라인 번호, 코멘트를 포함한 JSON 형식으로 응답하도록 프롬프트 수정
prompt = f"""
당신은 숙련된 소프트웨어 엔지니어입니다. 다음 코드 변경 사항에 대한 코드 리뷰를 제공해주세요:

{code_contents}

각 파일의 변경된 부분에 대해 라인 번호와 함께 리뷰 코멘트를 JSON 형식으로 제공해주세요.
예시:
[
    {{
        "file": "path/to/file.java",
        "line": 42,
        "comment": "이 부분에서 변수명이 명확하지 않습니다."
    }},
    ...
]

코드 품질, 잠재적 버그, 개선 사항 등에 대해 상세히 설명해주세요.
"""

response = requests.get(llm_api_url, params={'prompt': prompt})

if response.status_code == 200:
    llm_response = response.json().get('response', '리뷰 생성에 실패했습니다.')
else:
    llm_response = 'LLM API 요청에 실패했습니다.'

# LLM 응답 파싱
try:
    comments = json.loads(llm_response)
except json.JSONDecodeError:
    print("LLM 응답을 파싱하는 데 실패했습니다.")
    comments = []


# 라인 번호를 GitHub의 diff position으로 변환하는 함수
def line_to_position(patch, line_number):
    position = None
    current_line = None
    position_counter = 0
    for line in patch.split('\n'):
        if line.startswith('@@'):
            hunk_info = line.split(' ')[2]
            start_line = int(hunk_info.split(',')[0].replace('+', ''))
            current_line = start_line - 1
        elif line.startswith('+'):
            current_line += 1
            position_counter += 1
            if current_line == line_number:
                position = position_counter
                break
        elif line.startswith('-'):
            position_counter += 1
        else:
            current_line += 1
            position_counter += 1
    return position

# PR에 리뷰 코멘트 추가
for comment in comments:
    file_path = comment.get('file')
    line_number = comment.get('line')
    body = comment.get('comment')

    if file_path and line_number and body:
        # 변경된 파일인지 확인
        if file_path in file_diffs:
            patch = file_diffs[file_path]
            position = line_to_position(patch, line_number)
            if position is not None:
                pr.create_review_comment(body, pr.head.sha, file_path, position)
            else:
                print(f"{file_path}의 라인 {line_number}에 대한 position을 찾을 수 없습니다.")
        else:
            print(f"{file_path}는 변경된 파일 목록에 없습니다.")
    else:
        print("코멘트 정보가 부족하여 건너뜁니다:", comment)