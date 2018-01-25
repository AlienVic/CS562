package stevens.cs562.EMFQuery.Business;

import java.util.ArrayList;

public class EMFVariables {
	private ArrayList<String>  S;//SELECT ATTRIBUTE(S)
	private Integer N;//NUMBER OF GROUPING VARIABLES(n)
	private ArrayList<String> V;//GROUPING ATTRIBUTES(V)
	private ArrayList<String> F;//F-VECT([F])
	private ArrayList<ArrayList> Sigma;//SELECT CONDITION-VECT([ ])
	private ArrayList<String> G;//HAVING_CONDITION(G)
	private String where;//WHERE CLAUSE
	
	public ArrayList<String> getS() {
		return S;
	}
	public void setS(ArrayList<String> s) {
		S = s;
	}
	public Integer getN() {
		return N;
	}
	public void setN(Integer n) {
		N = n;
	}
	public ArrayList<String> getV() {
		return V;
	}
	public void setV(ArrayList<String> v) {
		V = v;
	}
	public ArrayList<String> getF() {
		return F;
	}
	public void setF(ArrayList<String> f) {
		F = f;
	}
	public ArrayList<ArrayList> getSigma() {
		return Sigma;
	}
	public void setSigma(ArrayList<ArrayList> varibles_sigmas) {
		Sigma = varibles_sigmas;
	}
	public ArrayList<String> getG() {
		return G;
	}
	public void setG(ArrayList<String> g) {
		G = g;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
}
