import docker

client = docker.from_env()
for i in client.containers.list():
    print(i.id)
client.containers.list()