import os
import requests
from github import Github
from github.GithubException import GithubException

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
    try:
        contents = repo.get_contents(file_path, ref=pr.head.ref)
        code = contents.decoded_content.decode('utf-8')
        code_contents += f"\n\n### 파일: {file_path}\n\n{code}"
    except GithubException as e:
        print(f"Failed to get contents of {file_path}: {e}")
        continue

# LLM API에 코드 리뷰 요청 보내기
llm_api_url = 'http://localhost:8000/llama3-2/3b/ask'  # FastAPI 서비스 주소
prompt = f"""
You are an experienced software engineer. Please provide a code review for the following code changes:

{code_contents}

Please clearly express and explain the modifications to the existing code concerning code quality, potential bugs, and improvements.
"""
print('request : ')
print(prompt)
response = requests.get(llm_api_url, params={'prompt': prompt})

if response.status_code == 200:
    review_comment = response.json().get('response', '리뷰 생성에 실패했습니다.')
else:
    review_comment = 'LLM API 요청에 실패했습니다.'

# PR에 리뷰 코멘트 추가
print('response : ')
print(review_comment)
pr.create_issue_comment(review_comment)