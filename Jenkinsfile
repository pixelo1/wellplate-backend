pipeline {
    agent any

    enviroment {
        // 백엔드 깃 REPO
        BACKEND_REPO_URL = "~~~.git"


        // 컨테이너 레지스트리 정보
        CONTAINER_REGISTRY_URL = "my-registry.com"
        IMAGE_NAME   = "backend"

        // k8s 매니페스트 Repo
        K8S_REPO_URL = "~~~.git"
        K8S_REPO_DIR = "well-plate"
    }


    stages {
        stage('Checkout Backend Code') {
            steps {
                // 백엔드 소스코드 (GitRepo #1) 클론
                git branch: 'main', url: ${BACKEND_REPO_URL}
            }
        }
        stage('Build & Test') {
            steps {
                // 예: Gradle 빌드
                sh './gradlew clean build'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    // 버전 태그 예시 (1.0.BUILD_NUMBER)
                    def version = "1.0." + env.BUILD_NUMBER
                    // 최종 이미지 태그
                    def fullImageTag = "${CONTAINER_REGISTRY_URL}/${IMAGE_NAME}:${version}"

                    // Docker build
                    sh "docker build -t ${fullImageTag} ."

                    // 로그인 필요 시 (ex: docker login my-registry.com)
                    // withCredentials(...) { ... } 구문 혹은 명령어

                    // Docker push
                    sh "docker push ${fullImageTag}"

                    // 파이프라인 환경변수에 저장
                    env.NEW_IMAGE_TAG = fullImageTag
                }
            }
        }
        stage('Update K8s Manifests') {
            steps {
                script {
                    // 1) K8s 매니페스트 Repo (GitRepo #2) 클론
                    sh "rm -rf ${K8S_REPO_DIR}"
                    sh "git clone ${K8S_REPO_URL} ${K8S_REPO_DIR}"

                    // 2) deployment.yaml에서 image: 줄 변경
                    // 예: backend/deployment.yaml 가 있다고 가정
                    sh """
                    sed -i 's|image: .*$|image: ${env.NEW_IMAGE_TAG}|' ${K8S_REPO_DIR}/backend/deployment.yaml
                    """

                    // 3) Git commit & push
                    dir("${K8S_REPO_DIR}") {
                        sh """
                        git config user.name "jenkins-bot"
                        git config user.email "jenkins@local"
                        git commit -am "Update backend image to ${env.NEW_IMAGE_TAG}"
                        git push origin main
                        """
                    }
                }
            }
        }
    }
}