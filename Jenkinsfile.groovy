node {
    properties([parameters([string(defaultValue: '127.0.0.1', description: '', name: 'IP', trim: true)])])
    stage("Install git"){
        sh "ssh ec2-user@${IP}     sudo yum install git python-pip -y"
    }
    stage("Clone a repo"){
        git 'https://github.com/rameca231190/flask-examples.git'
    }
    stage("Copy files"){
        sh "scp -r * ec2-user@${IP}:/tmp/"
    }
    stage("Install requirements"){
        sh "ssh ec2-user@${IP}    sudo pip install -t /tmp/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2-user@${IP}    python /tmp/01-hello-world/hello.py"
    }
}