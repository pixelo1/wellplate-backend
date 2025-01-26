pipeline {
    agent any

    tools {
        dockerTool 'docker_cli'
        jdk 'jdk_21_adoptium'
    }

    environment {
        // 백엔드 깃 REPO
        BACKEND_REPO_URL = "https://github.com/pixelo1/wellplate-backend.git"

        // 컨테이너 레지스트리 정보
        CONTAINER_REGISTRY_URL = "asia-northeast3-docker.pkg.dev/well-plate-448307/well-plate"
        IMAGE_NAME   = "health-backend"
        BACKEND_VERSION = "v1.0"

        // 도커 레지스트리 인증 정보
        DOCKER_CONFIG = "/var/jenkins_home/.docker"

        CLOUDSDK_CORE_PROJECT = 'well-plate-448307'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // 예: Gradle 빌드
                sh './gradlew clean build'
            }
        }
        stage('Build Image and Push to GCR') {
            steps {
                script {
                    def shortCommit = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                    def imageTag = "${CONTAINER_REGISTRY_URL}/${IMAGE_NAME}:${BACKEND_VERSION}-${shortCommit}"

                     // dockerconfigjson Secret 으로 이미 로그인 상태
                    sh """

                    docker build -t ${imageTag} .
                    docker push ${imageTag}
                    """
                }
            }
        }
    }
}