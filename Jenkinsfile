    pipeline
    {
    agent any
    stages
    {
    stage('Get code from git')
    {
    steps
    {
    echo 'Getting code from the git repo'
    git url:'https://github.com/amangarg-automation/Automation_Bank.git', branch:'docker'
    }
    }
    stage('Build and test')
    {
    steps
    {
    echo 'building the test classes and running tests'
    bat 'docker-compose up --build'
    }
    }
    stage('Archive junit reports')
    {
    steps
    {
    echo 'archiving junit reports'
    junit '**/target/surefire-reports/*.xml'
    }
    }
    }
    post
    {
    always
    {
    archiveArtifacts artifacts: '**/*.pdf', allowEmptyArchive:true
    }
    cleanup {
                echo 'Cleaning up Docker containers'
                bat 'docker-compose down'
            }
    }
    }