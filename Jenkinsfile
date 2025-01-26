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

//         도커 레지스트리 인증 정보
        DOCKER_CONFIG = "/var/jenkins_home/.docker"
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
        stage('Debug Credentials') {
            steps {
                script {
                    // 사용 가능한 credentials 목록 출력
                    def credentials = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
                        com.cloudbees.plugins.credentials.common.StandardCredentials.class,
                        Jenkins.instance,
                        null,
                        null
                    )
                    credentials.each {
                        println it.id
                    }
                }
            }
        }
        stage('Build Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'jenkins-gcr-key',
                                                  usernameVariable: 'DOCKER_USERNAME',
                                                  passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        def shortCommit = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                        def imageTag = "${CONTAINER_REGISTRY_URL}/${IMAGE_NAME}:${BACKEND_VERSION}-${shortCommit}"

                        sh """
                        echo \$DOCKER_PASSWORD | docker login ${CONTAINER_REGISTRY_URL} -u \$DOCKER_USERNAME --password-stdin

                        docker build --platform=linux/amd64 -t ${imageTag} .
                        docker push ${imageTag}

                        docker logout ${CONTAINER_REGISTRY_URL}
                        """
                    }
                }
            }
        }
    }
}