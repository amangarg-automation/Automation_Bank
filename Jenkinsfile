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
    git url:'https://github.com/amangarg-automation/Automation_Bank.git', branch:'feature/login'
    }
    }
    stage('Build and test')
    {
    steps
    {
    echo 'building the test classes and running tests'
    bat 'mvn clean test'
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
    }
    }