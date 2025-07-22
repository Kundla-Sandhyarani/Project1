pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MySonarQube') {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh 'mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN'
                    }
                }
            }
        }

        stage('Dependency Check') {
            steps {
                sh '''
                    mkdir -p $WORKSPACE/dc-data
                    /opt/dependency-check/bin/dependency-check.sh \
                      --project Project1 \
                      --scan . \
                      --format HTML \
                      --out dependency-report.html \
                      --data $WORKSPACE/dc-data
                '''
            }
        }

        stage('Publish Dependency Report') {
            steps {
                publishHTML target: [
                    reportDir: '.', 
                    reportFiles: 'dependency-report.html',
                    reportName: 'Dependency Check Report',
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ]
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t project1:${env.BUILD_NUMBER} ."
            }
        }

        stage('Trivy Scan') {
            steps {
                sh 'trivy image Project1'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 Project1'
            }
        }
    }
}
