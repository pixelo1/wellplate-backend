pipeline {
    agent any

    enviroment {
        // 백엔드 깃 REPO
        BACKEND_REPO_URL = "https://github.com/pixelo1/wellplate-backend.git"


        // 컨테이너 레지스트리 정보
        CONTAINER_REGISTRY_URL = "my-registry.com"
        IMAGE_NAME   = "backend"

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

    }
}