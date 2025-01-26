pipeline {
    agent any

    tools {
        dockerTool 'docker_cli'
        jdk 'jdk_21_adoptium'
    }

    environment {
        // 백엔드 깃 REPO
        BACKEND_REPO_URL = "https://github.com/pixelo1/wellplate-backend.git"

        K8S_REPO_URL = "github.com/pixelo1/well-plate-k8s.git"


        // 컨테이너 레지스트리 정보
        CONTAINER_REGISTRY_URL = "asia-northeast3-docker.pkg.dev/well-plate-448307/well-plate"
        IMAGE_NAME   = "health-backend"
        BACKEND_VERSION = "v1.0"

        // 도커 레지스트리 인증 정보
        DOCKER_CONFIG = "/var/jenkins_home/.docker"

        CLOUDSDK_CORE_PROJECT = 'well-plate-448307'

        // 수정할 아르고CD 매니페스트 경로
        ARGO_MANIFEST_PATH = "micro-k8s/well-plate/backend/well-plate-health.yaml"

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
                withCredentials([file(credentialsId: 'jenkins-gcr-key', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                    script {
                        def shortCommit = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                        def imageTag = "${CONTAINER_REGISTRY_URL}/${IMAGE_NAME}:${BACKEND_VERSION}-${shortCommit}"

                         // dockerconfigjson Secret 으로 이미 로그인 상태
                        sh """

                        gcloud auth activate-service-account --key-file=\$GOOGLE_APPLICATION_CREDENTIALS

                        docker build -t ${imageTag} .
                        docker push ${imageTag}
                        """
                        env.NEW_IMAGE_TAG = imageTag

                    }
                }
            }
        }

        stage('Checkout K8s Repo') {
            steps {
                // 별도의 디렉토리 'k8s-repo'에 well-plate-k8s 레포지토리를 체크아웃
                dir('k8s-repo') {
                    git branch: 'main',
                        credentialsId: 'new-pat-hoan1015-gmail',
                        url: "https://${K8S_REPO_URL}"
                }
            }
        }

        stage('Bump Kubernetes YAML') {
            steps {
                dir('k8s-repo') {
                    // GitHub에 푸시할 준비를 위해 Git 설정
                    withCredentials([usernamePassword(credentialsId: 'new-pat-hoan1015-gmail', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        script {
                            sh """
                            # 1) Git 사용자 설정
                            git config user.email "hoan1015@gmail.com"
                            git config user.name ${GIT_USERNAME}

                            # 2) YAML 파일에서 image 태그 교체
                            sed -i 's|image:.*|image: ${env.NEW_IMAGE_TAG}|' ${env.ARGO_MANIFEST_PATH}

                            # 3) 변경 사항 커밋
                            git add ${env.ARGO_MANIFEST_PATH}
                            git commit -m "Update backend image to ${env.NEW_IMAGE_TAG}"

                            # 4) Git push
                            git remote set-url origin https://${GIT_USERNAME}:${GIT_PASSWORD}@${K8S_REPO_URL}
                            git push origin HEAD:main

                            """
                        }
                    }
                }
            }
        }

    }
}