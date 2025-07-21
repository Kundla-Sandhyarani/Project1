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
                sh './dependency-check.sh'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t Project1 .'
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
                sh './dependency-check.sh'
            }
        }

        stage('Docker Build') {
