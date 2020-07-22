​	此项目中的File-Project Structure-Modules中project和module的Language level需要和Preferences-Java compiler中的Project bytecode version一致;

​	使用idea开发若出现404可在File-Project Structure-Artifacts中选择当前module，在WEB-INF中新建lib目录，点击上面的"+"把所有的Library加入该目录，重新编译即可。

​	新建module时选择maven项目即可，成功后右键项目选择"Add Framework Support..."可添加web支持;

​	新建selvlet后需要在pom.xml中注册;

​	依赖需要在pom.xml中导入;

​	在Run Configurations的Deployment中可设置访问路径;