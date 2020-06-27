package com.geronimodesenvolvimentos.gameservice.model;

public class Vector3 {
    private float x;
    private float y;
    private float z;
	public float getX() {
		return x;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setX(float x) {
		this.x = x;
	}
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
    }
    public Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    @Override
    public boolean equals(Object o){
        if(o == null || o instanceof Vector3 == false){
            return false;
        }else{
            Vector3 obj = (Vector3)o;
            float E = 10e-4f;
            return Math.abs(x-obj.x)<=E && Math.abs(y-obj.y)<=E && Math.abs(z-obj.z)<=E;
        }
    }
    @Override
    public int hashCode(){
        return new Float(x).hashCode() + new Float(y).hashCode() * 13 + new Float(z).hashCode() * 31;
    }
}