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
    # if file_path.endswith('.py'):  # 필요한 확장자로 필터링
    contents = repo.get_contents(file_path, ref=pr.head.ref)
    code = contents.decoded_content.decode('utf-8')
    code_contents += f"\n\n### 파일: {file_path}\n\n{code}"

# LLM API에 코드 리뷰 요청 보내기
llm_api_url = 'http://localhost:8000/llama3-2/3b/ask'  # FastAPI 서비스 주소
prompt = f"""
you are master of software enginer.
Review the provided code by checking Pre-Conditions. Ensure that all required variables hold the correct values and are within expected ranges before function execution. Highlight any invalid pre-conditions and suggest corrections. Provide step-by-step validation to avoid false positives. Consistent format required.
Analyze the code for Runtime Errors. Identify potential runtime failures, such as unhandled exceptions or risky operations. Validate each case to confirm issues and highlight problematic lines. Suggest solutions for mitigating runtime risks. Keep the review in a structured format for clarity.
Inspect the code for Optimization opportunities. If performance issues are detected, propose more efficient alternatives. Use benchmarks or examples to support suggestions. Highlight optimized portions for clear comparison. Always ensure validation of changes before recommendations.
Check the code for any Security Issues. Look for vulnerable modules or coding practices that introduce security flaws. Validate the severity of each issue and offer fixes. Highlight affected areas clearly for ease of understanding. Consistency in format and explanation is crucial. 
After reviewing Pre-Condition, Runtime Error, Optimization, and Security Issues, provide a final report. Ensure all changes and suggestions are validated. Highlight modified sections and maintain a consistent review format for easy comparison.
Please provide code reviews for the following code changes:

{code_contents}
"""

response = requests.get(llm_api_url, params={'prompt': prompt})

if response.status_code == 200:
    review_comment = response.json().get('response', '리뷰 생성에 실패했습니다.')
else:
    review_comment = 'LLM API 요청에 실패했습니다.'

# PR에 리뷰 코멘트 추가
pr.create_issue_comment(review_comment)