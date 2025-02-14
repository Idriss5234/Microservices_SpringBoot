data "aws_ami" "latest_Amazon_linux_image"{
    most_recent=true
    owners=["amazon"]
    filter {
        name="name"
        values=["amzn2-ami-hvm-*-x86_64-gp2"]
    }
    filter{
        name="virtualization-type"
        values=["hvm"]
    }
}