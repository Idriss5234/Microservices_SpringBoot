resource "aws_instance" "ms_ec2" {
    ami= data.aws_ami.latest_Amazon_linux_image.id
    instance_type="t3.micro"
    subnet_id=aws_subnet.ms_subnets.id
    availability_zone=var.avail_zone
    vpc_security_group_ids=[aws_security_group.ms_sg.id]
    associate_public_ip_address=true

    key_name= aws_key_pair.ms_key_pair.key_name

    tags={
        name="${var.env_prefix}-server"
    }

    connection{
    type="ssh"
    host = self.public_ip
    user="ec2-user"
    private_key=file(var.private_key_location)
 }

    
}

resource "aws_key_pair" "ms_key_pair" {
    key_name = "ms-key-pair"
    public_key = file(var.public_key_location)
  
}

resource "null_resource" "configure_server" {
    provisioner "local-exec" {
        working_dir = var.local_exec_dir
        command = "ansible-playbook -i ${aws_instance.ms_ec2.public_ip}, --private-key ${var.private_key_location} --user ec2-user,  ms_playbook.yaml"
      
    }
}