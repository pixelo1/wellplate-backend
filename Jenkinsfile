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
    }


    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

//         stage('Checkout Backend Code') {
//             steps {
//                 // 백엔드 소스코드 (GitRepo #1) 클론
//                 git branch: 'main', url: "${BACKEND_REPO_URL}"
//             }
//         }
        stage('Build & Test') {
            steps {
                // 예: Gradle 빌드
//                 sh './gradlew clean build -Dspring.profiles.active=test-no-container'
                sh './gradlew clean build'
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
                        docker build --platform=linux/amd64 --no-cache -t ${imageTag} .
                        docker push ${imageTag}
                        """
                   }
                }
            }
        }
    }
}