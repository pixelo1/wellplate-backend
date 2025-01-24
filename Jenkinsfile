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

        // k8s 매니페스트 Repo
        K8S_REPO_URL = "~~~.git"
        K8S_REPO_DIR = "well-plate"

//         도커 레지스트리 인증 정보
        DOCKER_CONFIG = "/var/jenkins_home/.docker"
        TESTCONTAINERS_RYUK_DISABLED = 'true' // 테스트 컨테이너 ryuk 비활성화
//         TESTCONTAINERS_HOST_OVERRIDE = 'host.docker.internal'
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
                sh './gradlew clean build --debug'

            }
        }
        stage('Build Image') {
            steps {
                withCredentials([dockerConfigJson(credentialsId: 'kubernetes://well-plate/gcr-json-key')]) {
                   script {
                       def shortCommit = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                       //v1.0-<커밋해시>
                       def imageTag = "${CONTAINER_REGISTRY_URL}/${IMAGE_NAME}:${BACKEND_VERSION}-${shortCommit}"

                        sh """
                        docker build --platform=linux/amd64 -t ${imageTag} .
                        docker push ${imageTag}
                        """
                   }
                }
            }
        }
    }
    post {
        always {
            // 빌드 후 Docker 컨테이너 정리
            sh '''
            docker container prune -f
            docker image prune -f
            '''
        }
    }
}