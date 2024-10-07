import os
import requests
from github import Github

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

# 코드 내용 가져오기
code_contents = ""
for file_path in changed_files:
    if file_path.endswith('.py'):  # 필요한 확장자로 필터링
        contents = repo.get_contents(file_path, ref=pr.head.ref)
        code = contents.decoded_content.decode('utf-8')
        code_contents += f"\n\n### 파일: {file_path}\n\n{code}"

# LLM API에 코드 리뷰 요청 보내기
llm_api_url = 'http://localhost:8000/llama3-2/3b/ask'  # FastAPI 서비스 주소
prompt = f"""
당신은 숙련된 소프트웨어 엔지니어입니다. 다음 코드 변경 사항에 대한 코드 리뷰를 제공해주세요:

{code_contents}

코드 품질, 잠재적 버그, 개선 사항 등에 대해 상세히 설명해주세요.
"""

response = requests.get(llm_api_url, params={'prompt': prompt})

if response.status_code == 200:
    review_comment = response.json().get('response', '리뷰 생성에 실패했습니다.')
else:
    review_comment = 'LLM API 요청에 실패했습니다.'

# PR에 리뷰 코멘트 추가
pr.create_issue_comment(review_comment)