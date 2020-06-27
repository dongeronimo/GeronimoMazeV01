cp -r ../../Builds/webgl/ .
sudo docker image build -t geronimo_maze_v01/webgl .
rm -rf webgl
