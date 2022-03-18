pipeline {
    agent any

    stages {

        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo "On branch 'env.BRANCH_NAME'" + env
                bat 'gradlew test'
            }

        }

        stage('deploy'){
            steps {
                echo 'Deployed!!'
            }
        }
    }
}