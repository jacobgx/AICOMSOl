/*
 * engine_fatigue.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:04 by COMSOL 6.3.0.290. */
public class engine_fatigue {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Stress_Life");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/c", true);

    model.component("comp1").label("Thermodynamic Analysis");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "reciprocating_engine_2d.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "pi-pi/60");
    model.component("comp1").func("rect1").set("upper", "pi+pi/30");
    model.component("comp1").func("rect1").set("smooth", "pi/120");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4, 5, 15, 18, 19, 27);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("rp", "40[mm]");
    model.component("comp1").variable("var1").descr("rp", "Radius of piston");
    model.component("comp1").variable("var1").set("rc", "40[mm]");
    model.component("comp1").variable("var1").descr("rc", "Radius of crank");
    model.component("comp1").variable("var1").set("l", "200[mm]");
    model.component("comp1").variable("var1").descr("l", "Length of connecting rod");
    model.component("comp1").variable("var1").set("omega", "(1000*2*pi/60)[rad/s]");
    model.component("comp1").variable("var1").descr("omega", "Angular velocity of crankshaft");
    model.component("comp1").variable("var1").set("theta", "omega*t");
    model.component("comp1").variable("var1").descr("theta", "Rotation of crankshaft");
    model.component("comp1").variable("var1").set("xp", "-rc*cos(theta)+sqrt(l^2-(rc*sin(theta))^2)+rc-l");
    model.component("comp1").variable("var1").descr("xp", "Piston displacement");
    model.component("comp1").variable("var1").set("V0", "intop1(2*pi*r)");
    model.component("comp1").variable("var1").descr("V0", "Initial cylinder volume");
    model.component("comp1").variable("var1").set("V", "V0-pi*rp^2*xp");
    model.component("comp1").variable("var1").descr("V", "Current cylinder volume");
    model.component("comp1").variable("var1").set("rho0", "1.1886[kg/m^3]");
    model.component("comp1").variable("var1").descr("rho0", "Air density at STP");
    model.component("comp1").variable("var1").set("m", "rho0*V0");
    model.component("comp1").variable("var1").descr("m", "Mass of air");
    model.component("comp1").variable("var1").set("R_air", "287[J/kg/K]");
    model.component("comp1").variable("var1").descr("R_air", "Specific gas constant of air");
    model.component("comp1").variable("var1").set("Q", "600[J]");
    model.component("comp1").variable("var1").descr("Q", "Heat generated during combustion");
    model.component("comp1").variable("var1").set("Pi", "Q*omega/(pi[rad]/20)*rect1(theta)");
    model.component("comp1").variable("var1").descr("Pi", "Power input");
    model.component("comp1").variable("var1").set("Po", "intop2(p*2*pi*r*d(xp,t)*root.nz)");
    model.component("comp1").variable("var1").descr("Po", "Power output");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("c").selection().set(2);
    model.component("comp1").physics("c").prop("Units").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("c").prop("Units").set("SourceTermQuantity", "pressure");
    model.component("comp1").physics("c").field("dimensionless").field("p");
    model.component("comp1").physics("c").field("dimensionless").component(1, "p");
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", new int[]{0, 0, 0, 0}, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("a", 1, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", "m/V*R_air*T", 0);
    model.component("comp1").physics("c").feature("init1").set("p", "1e5");
    model.component("comp1").physics("ht").selection().set(2);
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure", "p");
    model.component("comp1").physics("ht").feature("fluid1").create("pw1", "PressureWork", 2);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(2);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "Pi/V");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("Study: Thermodynamic Analysis");
    model.study("std1").feature("time").set("tlist", "range(0,0.0006,0.06)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("PV Diagram");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(2);
    model.result("pg1").feature("ptgr1").set("expr", "p");
    model.result("pg1").feature("ptgr1").set("unit", "bar");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "V");
    model.result("pg1").feature("ptgr1").set("xdataunit", "ml");
    model.result("pg1").feature("ptgr1").set("linewidth", 2);
    model.result("pg1").feature("ptgr1").set("titletype", "none");
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Pressure (bar)");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Volume (cc)");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Cylinder Pressure");
    model.result("pg2").set("xlabel", "Rotation of crankshaft (rad)");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").set("xdataexpr", "theta");
    model.result("pg2").run();
    model.result().export().create("plot1", "pg2", "ptgr1", "Plot");
    model.result().export("plot1").set("filename", "reciprocating_engine_pressure.txt");
    model.result().export("plot1").set("header", false);
    model.result().export("plot1").set("fullprec", false);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "timeint(0, 0.06, Po)", 0);
    model.result().numerical("gev1").setIndex("unit", "J", 0);
    model.result().numerical("gev1").setIndex("descr", "Mechanical Energy", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("mbd", "MultibodyDynamics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/mbd", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", false);
    model.study("std2").feature("time").setSolveFor("/physics/c", false);
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);

    model.component("comp2").label("Multibody Analysis");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "reciprocating_engine.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("imp1(1)", "imp1(11)");
    model.component("comp2").geom("geom2").feature("uni1").set("intbnd", false);
    model.component("comp2").geom("geom2").feature("fin").set("action", "assembly");
    model.component("comp2").geom("geom2").run("fin");

    model.nodeGroup().create("grp1", "Definitions", "comp2");
    model.nodeGroup("grp1").set("type", "pair");
    model.nodeGroup("grp1").add("pair", "ap1");
    model.nodeGroup("grp1").add("pair", "ap2");
    model.nodeGroup("grp1").add("pair", "ap3");
    model.nodeGroup("grp1").add("pair", "ap4");
    model.nodeGroup("grp1").add("pair", "ap6");
    model.nodeGroup("grp1").add("pair", "ap8");
    model.nodeGroup("grp1").label("Hinge Joint Pairs");
    model.nodeGroup().create("grp2", "Definitions", "comp2");
    model.nodeGroup("grp2").set("type", "pair");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("pair", "ap5");
    model.nodeGroup("grp2").add("pair", "ap7");
    model.nodeGroup("grp2").add("pair", "ap9");
    model.nodeGroup("grp2").label("Prismatic Joint Pairs");
    model.nodeGroup("grp2").active(false);

    model.component("comp2").func().create("int1", "Interpolation");
    model.component("comp2").func("int1")
         .set("table", new String[][]{{"0", "1.000024"}, 
         {"0.062832", "1.001029"}, 
         {"0.125664", "1.004054"}, 
         {"0.188496", "1.009121"}, 
         {"0.251327", "1.01632"}, 
         {"0.314159", "1.025671"}, 
         {"0.376991", "1.03716"}, 
         {"0.439823", "1.051273"}, 
         {"0.502655", "1.068191"}, 
         {"0.565487", "1.087674"}, 
         {"0.628319", "1.109722"}, 
         {"0.69115", "1.134336"}, 
         {"0.753982", "1.161514"}, 
         {"0.816814", "1.192159"}, 
         {"0.879646", "1.229792"}, 
         {"0.942478", "1.271355"}, 
         {"1.00531", "1.316848"}, 
         {"1.068142", "1.366271"}, 
         {"1.130973", "1.419625"}, 
         {"1.193805", "1.481374"}, 
         {"1.256637", "1.555095"}, 
         {"1.319469", "1.636019"}, 
         {"1.382301", "1.724148"}, 
         {"1.445133", "1.819482"}, 
         {"1.507964", "1.932802"}, 
         {"1.570796", "2.064526"}, 
         {"1.633628", "2.209666"}, 
         {"1.69646", "2.368221"}, 
         {"1.759292", "2.557812"}, 
         {"1.822124", "2.778199"}, 
         {"1.884956", "3.023069"}, 
         {"1.947787", "3.306307"}, 
         {"2.010619", "3.646934"}, 
         {"2.073451", "4.030405"}, 
         {"2.136283", "4.487249"}, 
         {"2.199115", "5.026185"}, 
         {"2.261947", "5.648982"}, 
         {"2.324779", "6.40657"}, 
         {"2.38761", "7.278821"}, 
         {"2.450442", "8.322606"}, 
         {"2.513274", "9.524051"}, 
         {"2.576106", "10.946847"}, 
         {"2.638938", "12.573095"}, 
         {"2.70177", "14.427509"}, 
         {"2.764602", "16.482005"}, 
         {"2.827433", "18.668079"}, 
         {"2.890265", "20.876783"}, 
         {"2.953097", "22.874979"}, 
         {"3.015929", "24.478569"}, 
         {"3.078761", "25.808781"}, 
         {"3.141593", "43.499878"}, 
         {"3.204425", "60.819261"}, 
         {"3.267256", "68.936071"}, 
         {"3.330088", "64.415409"}, 
         {"3.39292", "58.678623"}, 
         {"3.455752", "52.600459"}, 
         {"3.518584", "46.65871"}, 
         {"3.581416", "40.848583"}, 
         {"3.644247", "35.674817"}, 
         {"3.707079", "31.166582"}, 
         {"3.769911", "27.24531"}, 
         {"3.832743", "23.883555"}, 
         {"3.895575", "21.071102"}, 
         {"3.958407", "18.630487"}, 
         {"4.021239", "16.592263"}, 
         {"4.08407", "14.829826"}, 
         {"4.146902", "13.334514"}, 
         {"4.209734", "12.043352"}, 
         {"4.272566", "10.932525"}, 
         {"4.335398", "9.991426"}, 
         {"4.39823", "9.156265"}, 
         {"4.461062", "8.436079"}, 
         {"4.523893", "7.813539"}, 
         {"4.586725", "7.263721"}, 
         {"4.649557", "6.780149"}, 
         {"4.712389", "6.355781"}, 
         {"4.775221", "5.985243"}, 
         {"4.838053", "5.651274"}, 
         {"4.900885", "5.357125"}, 
         {"4.963716", "5.101169"}, 
         {"5.026548", "4.865974"}, 
         {"5.08938", "4.656378"}, 
         {"5.152212", "4.472382"}, 
         {"5.215044", "4.304574"}, 
         {"5.277876", "4.152206"}, 
         {"5.340708", "4.018609"}, 
         {"5.403539", "3.896786"}, 
         {"5.466371", "3.789793"}, 
         {"5.529203", "3.695939"}, 
         {"5.592035", "3.609998"}, 
         {"5.654867", "3.534864"}, 
         {"5.717699", "3.468956"}, 
         {"5.78053", "3.411485"}, 
         {"5.843362", "3.363203"}, 
         {"5.906194", "3.321215"}, 
         {"5.969026", "3.286384"}, 
         {"6.031858", "3.258718"}, 
         {"6.09469", "3.237066"}, 
         {"6.157522", "3.222023"}, 
         {"6.220353", "3.213244"}, 
         {"6.283185", "3.210417"}});
    model.component("comp2").func("int1").set("funcname", "p");
    model.component("comp2").func("int1").setIndex("argunit", "rad", 0);
    model.component("comp2").func("int1").setIndex("fununit", "bar", 0);

    model.component("comp2").selection().create("cyl1", "Cylinder");
    model.component("comp2").selection("cyl1").set("entitydim", 2);
    model.component("comp2").selection("cyl1").set("r", 0.03);
    model.component("comp2").selection("cyl1").set("bottom", -0.081);
    model.component("comp2").selection("cyl1").set("pos", new double[]{-0.11, 0, 0});
    model.component("comp2").selection().duplicate("cyl2", "cyl1");
    model.component("comp2").selection("cyl2").set("bottom", -0.013);
    model.component("comp2").selection("cyl2").set("pos", new int[]{0, 0, 0});
    model.component("comp2").selection().duplicate("cyl3", "cyl2");
    model.component("comp2").selection("cyl3").set("bottom", -0.07);
    model.component("comp2").selection("cyl3").set("pos", new double[]{0.11, 0, 0});
    model.component("comp2").selection().create("sel1", "Explicit");
    model.component("comp2").selection("sel1").set(1, 2, 3, 4, 5, 7, 8, 9, 10);
    model.component("comp2").selection("sel1").label("Rigid Materials");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp2").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp2").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp2").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp2").material("mat2").label("Structural steel");
    model.component("comp2").material("mat2").set("family", "custom");
    model.component("comp2").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat2").set("diffuse", "custom");
    model.component("comp2").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("ambient", "custom");
    model.component("comp2").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("noise", true);
    model.component("comp2").material("mat2").set("fresnel", 0.9);
    model.component("comp2").material("mat2").set("roughness", 0.3);
    model.component("comp2").material("mat2").set("diffusewrap", 0);
    model.component("comp2").material("mat2").set("reflectance", 0);
    model.component("comp2").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "E(T)");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp2").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp2").physics("mbd").prop("AutoModeling").set("RigidDomSelection", "sel1");
    model.component("comp2").physics("mbd").prop("AutoModeling").runCommand("createRigidDomains");
    model.component("comp2").physics("mbd").feature("rd1").label("Crankshaft");
    model.component("comp2").physics("mbd").feature("rd2").label("Piston 1");
    model.component("comp2").physics("mbd").feature("rd3").label("Connecting Rod 1");
    model.component("comp2").physics("mbd").feature("rd4").label("Cylinder 1");
    model.component("comp2").physics("mbd").feature("rd5").label("Piston 2");
    model.component("comp2").physics("mbd").feature("rd6").label("Cylinder 2");
    model.component("comp2").physics("mbd").feature("rd7").label("Piston 3");
    model.component("comp2").physics("mbd").feature("rd8").label("Connecting Rod 3");
    model.component("comp2").physics("mbd").feature("rd9").label("Cylinder 3");
    model.component("comp2").physics("mbd").feature("rd4").create("fix1", "FixedConstraint", -1);
    model.component("comp2").physics("mbd").feature("rd6").create("fix1", "FixedConstraint", -1);
    model.component("comp2").physics("mbd").feature("rd9").create("fix1", "FixedConstraint", -1);
    model.component("comp2").physics("mbd").prop("AutoModeling").set("PlnBnd", "none");
    model.component("comp2").physics("mbd").prop("AutoModeling").set("SprBnd", "none");
    model.component("comp2").physics("mbd").prop("AutoModeling").runCommand("createJoints");
    model.component("comp2").physics("mbd").feature("att1").label("Connecting Rod 2: Bottom End");
    model.component("comp2").physics("mbd").feature("att1").set("ConnectionType", "FlexibleType");
    model.component("comp2").physics("mbd").feature("att2").label("Connecting Rod 2: Top End");
    model.component("comp2").physics("mbd").feature("att2").set("ConnectionType", "FlexibleType");
    model.component("comp2").physics("mbd").feature().duplicate("hgj7", "hgj6");

    model.nodeGroup("grp4").add("hgj7");

    model.component("comp2").physics("mbd").feature("hgj7").set("Source", "fixed");
    model.component("comp2").physics("mbd").feature("hgj7").set("Destination", "rd1");
    model.component("comp2").physics("mbd").feature("hgj7").set("CenterOfJointType", "CentroidOfSelectedEntities");
    model.component("comp2").physics("mbd").feature("hgj7").set("EntityLevel", "Edge");
    model.component("comp2").physics("mbd").feature("hgj7").feature("cje1").selection().set(21, 24);
    model.component("comp2").physics("mbd").feature().duplicate("hgj8", "hgj7");

    model.nodeGroup("grp4").add("hgj8");

    model.component("comp2").physics("mbd").feature("hgj8").feature("cje1").selection().set(297, 298);

    model.nodeGroup("grp1").active(false);
    model.nodeGroup("grp2").active(true);

    model.component("comp2").physics("mbd").prop("AutoModeling").set("CylBnd", "PrismaticJoint");
    model.component("comp2").physics("mbd").prop("AutoModeling").runCommand("createJoints");

    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").selection().geom("geom2", 2);
    model.component("comp2").cpl("intop3").selection().named("cyl1");
    model.component("comp2").cpl("intop3").set("frame", "material");

    model.component("comp2").func().create("step1", "Step");
    model.component("comp2").func("step1").set("location", "pi/3");
    model.component("comp2").func("step1").set("from", 1);
    model.component("comp2").func("step1").set("to", 0);
    model.component("comp2").func("step1").set("smooth", "pi/36");
    model.component("comp2").func().create("step2", "Step");
    model.component("comp2").func("step2").set("location", "3*pi");
    model.component("comp2").func("step2").set("smooth", "pi/18");

    model.component("comp2").cpl().create("maxop1", "Maximum");
    model.component("comp2").cpl("maxop1").selection().set(6);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("theta", "abs(mbd.hgj7.th)", "Rotation of crankshaft");
    model.component("comp2").variable("var2").descr("theta", "Rotation of crankshaft");
    model.component("comp2").variable("var2").set("theta0", "50.25[deg]");
    model.component("comp2").variable("var2").descr("theta0", "Initial rotation of crank 1");
    model.component("comp2").variable("var2").set("N", "d(theta,t)*60/(2*pi)");
    model.component("comp2").variable("var2").descr("N", "RPM of crankshaft");
    model.component("comp2").variable("var2").set("Ti", "400[N*m]*step1(theta)");
    model.component("comp2").variable("var2").descr("Ti", "Starting torque");
    model.component("comp2").variable("var2").set("To", "0.5[N*m*s/rad]*d(theta,t)*step2(theta)");
    model.component("comp2").variable("var2").descr("To", "Output torque");
    model.component("comp2").variable("var2").set("p1", "p(mod(theta-theta0,2*pi))");
    model.component("comp2").variable("var2").descr("p1", "Pressure in cylinder 1");
    model.component("comp2").variable("var2").set("p2", "p(mod(theta-theta0+4*pi/3,2*pi))");
    model.component("comp2").variable("var2").descr("p2", "Pressure in cylinder 2");
    model.component("comp2").variable("var2").set("p3", "p(mod(theta-theta0+2*pi/3,2*pi))");
    model.component("comp2").variable("var2").descr("p3", "Pressure in cylinder 3");
    model.component("comp2").variable("var2").set("A", "intop3(root.nZ)");
    model.component("comp2").variable("var2").descr("A", "Projected area of piston");
    model.component("comp2").variable("var2").set("P1", "-p1*A*mbd.prj1.u_t/746[W]");
    model.component("comp2").variable("var2").descr("P1", "Power generated in cylinder 1 (hp)");
    model.component("comp2").variable("var2").set("P2", "-p2*A*mbd.prj2.u_t/746[W]");
    model.component("comp2").variable("var2").descr("P2", "Power generated in cylinder 2 (hp)");
    model.component("comp2").variable("var2").set("P3", "-p3*A*mbd.prj3.u_t/746[W]");
    model.component("comp2").variable("var2").descr("P3", "Power generated in cylinder 3 (hp)");
    model.component("comp2").variable("var2").set("BHP", "To*d(theta,t)/746[W]");
    model.component("comp2").variable("var2").descr("BHP", "Brake horse power");
    model.component("comp2").variable("var2").set("MaxStress_cr", "maxop1(mbd.mises)");
    model.component("comp2").variable("var2").descr("MaxStress_cr", "Maximum stress in connecting rod");

    model.component("comp2").physics("mbd").feature("rd1").create("am1", "AppliedMoment", -1);
    model.component("comp2").physics("mbd").feature("rd1").feature("am1").set("Mt", new String[]{"Ti", "0", "0"});
    model.component("comp2").physics("mbd").feature("rd1").feature().duplicate("am2", "am1");
    model.component("comp2").physics("mbd").feature("rd1").feature("am2").set("Mt", new String[]{"-To", "0", "0"});
    model.component("comp2").physics("mbd").create("bndl1", "BoundaryLoad", 2);
    model.component("comp2").physics("mbd").feature("bndl1").selection().named("cyl1");
    model.component("comp2").physics("mbd").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp2").physics("mbd").feature("bndl1").set("pressure", "p1");
    model.component("comp2").physics("mbd").create("bndl2", "BoundaryLoad", 2);
    model.component("comp2").physics("mbd").feature("bndl2").selection().named("cyl2");
    model.component("comp2").physics("mbd").feature("bndl2").set("forceType", "FollowerPressure");
    model.component("comp2").physics("mbd").feature("bndl2").set("pressure", "p2");
    model.component("comp2").physics("mbd").create("bndl3", "BoundaryLoad", 2);
    model.component("comp2").physics("mbd").feature("bndl3").selection().named("cyl3");
    model.component("comp2").physics("mbd").feature("bndl3").set("forceType", "FollowerPressure");
    model.component("comp2").physics("mbd").feature("bndl3").set("pressure", "p3");

    model.nodeGroup().create("grp6", "Physics", "mbd");
    model.nodeGroup("grp6").placeAfter("init1");
    model.nodeGroup("grp6").add("bndl1");
    model.nodeGroup("grp6").add("bndl2");
    model.nodeGroup("grp6").add("bndl3");
    model.nodeGroup("grp6").label("Boundary Loads");

    model.component("comp2").mesh("mesh2").contribute("geom/detail", false);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").label("Study: Multibody Analysis");
    model.study("std2").feature("time").set("tlist", "range(0,4e-4,0.16)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp2_mbd_att1_Fc1x")
         .set("scaleval", "1e8*(0.1*0.7011179332388733)^2*100");
    model.sol("sol2").feature("v1").feature("comp2_mbd_att1_Fd1x")
         .set("scaleval", "1e8*(0.1*0.7011179332388733)*100");
    model.sol("sol2").feature("v1").feature("comp2_mbd_att2_Fc1x")
         .set("scaleval", "1e8*(0.1*0.7011179332388733)^2*100");
    model.sol("sol2").feature("v1").feature("comp2_mbd_att2_Fd1x")
         .set("scaleval", "1e8*(0.1*0.7011179332388733)*100");
    model.sol("sol2").feature("t1").set("tstepsbdf", "free");
    model.sol("sol2").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol2").feature("t1").set("maxstepbdf", "4e-4");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Displacement (mbd)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("def1", "Deform");
    model.result("pg3").feature("surf1").feature("def1").label("Deformation");
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Velocity (mbd)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("Domain Numbers");
    model.result("pg4").feature("vol1").set("descractive", true);
    model.result("pg4").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg4").feature("vol1").set("descr", "Domain numbers");
    model.result("pg4").feature("vol1").set("rangecoloractive", "on");
    model.result("pg4").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg4").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg4").feature("vol1").set("colortable", "Cyclic");
    model.result("pg4").feature("vol1").set("colorlegend", false);
    model.result("pg4").feature("vol1").set("colortabletype", "discrete");
    model.result("pg4").feature("vol1").set("smooth", "none");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").feature().create("def1", "Deform");
    model.result("pg4").feature("vol1").feature("def1").label("Deformation");
    model.result("pg4").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").label("Arrow Line");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg4").feature("arwl1").set("placement", "elements");
    model.result("pg4").feature("arwl1").set("data", "parent");
    model.result("pg4").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg4").feature("arwl1").feature("def1").label("Deformation");
    model.result("pg4").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg3").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().set(1, 2, 3, 5, 6, 8, 9);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "dset4");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("unit", "mm");
    model.result("pg3").run();
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("titletype", "none");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").feature("vol1").set("color", "gray");
    model.result("pg3").feature("vol1").create("sel1", "Selection");
    model.result("pg3").feature("vol1").feature("sel1").selection().set(4, 7, 10);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 401, 0);
    model.result("pg5").label("Boundary Loads (mbd)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg5").feature("surf1").label("Gray Surfaces");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature("surf1").set("inheritcolor", false);
    model.result("pg5").feature("surf1").set("inheritrange", false);
    model.result("pg5").feature("surf1").set("inherittransparency", false);
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", 1);
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom2", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913);
    model.result("pg5").feature("surf1").create("tran1", "Transparency");
    model.result("pg5").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1")
         .set("expr", new String[]{"mbd.bndl1.fax", "mbd.bndl1.fay", "mbd.bndl1.faz"});
    model.result("pg5").feature("arws1").set("placement", "gausspoints");
    model.result("pg5").feature("arws1").set("arrowbase", "head");
    model.result("pg5").feature("arws1").label("Boundary Load 1");
    model.result("pg5").feature("arws1").set("inheritplot", "none");
    model.result("pg5").feature("arws1").create("col", "Color");
    model.result("pg5").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg5").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("arws1").set("color", "red");
    model.result("pg5").feature("arws1").create("def", "Deform");
    model.result("pg5").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws1").feature("def").set("descr", "Displacement field");
    model.result("pg5").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws1").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 1);
    model.result("pg5").create("arws2", "ArrowSurface");
    model.result("pg5").feature("arws2")
         .set("expr", new String[]{"mbd.bndl2.fax", "mbd.bndl2.fay", "mbd.bndl2.faz"});
    model.result("pg5").feature("arws2").set("placement", "gausspoints");
    model.result("pg5").feature("arws2").set("arrowbase", "head");
    model.result("pg5").feature("arws2").label("Boundary Load 2");
    model.result("pg5").feature("arws2").set("inheritplot", "arws1");
    model.result("pg5").feature("arws2").create("col", "Color");
    model.result("pg5").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg5").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("arws2").set("color", "red");
    model.result("pg5").feature("arws2").create("def", "Deform");
    model.result("pg5").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws2").feature("def").set("descr", "Displacement field");
    model.result("pg5").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws2").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 2);
    model.result("pg5").create("arws3", "ArrowSurface");
    model.result("pg5").feature("arws3")
         .set("expr", new String[]{"mbd.bndl3.fax", "mbd.bndl3.fay", "mbd.bndl3.faz"});
    model.result("pg5").feature("arws3").set("placement", "gausspoints");
    model.result("pg5").feature("arws3").set("arrowbase", "head");
    model.result("pg5").feature("arws3").label("Boundary Load 3");
    model.result("pg5").feature("arws3").set("inheritplot", "arws2");
    model.result("pg5").feature("arws3").create("col", "Color");
    model.result("pg5").feature("arws3").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws3").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws3").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws3").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws3").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws3").feature("col").set("topcolor", "red");
    model.result("pg5").feature("arws3").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws3").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("arws3").set("color", "red");
    model.result("pg5").feature("arws3").create("def", "Deform");
    model.result("pg5").feature("arws3").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws3").feature("def").set("descr", "Displacement field");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").feature("arws3").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws3").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 3);
    model.result("pg5").label("Boundary Loads (mbd)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("arws1", "pg5/arws1");
    model.result("pg3").feature().copy("arws2", "pg5/arws2");
    model.result("pg3").feature().copy("arws3", "pg5/arws3");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("col").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").feature("arws2").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("arws3").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().dataset().duplicate("dset5", "dset3");
    model.result().dataset("dset5").selection().geom("geom2", 3);
    model.result().dataset("dset5").selection().geom("geom2", 3);
    model.result().dataset("dset5").selection().set(6);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("Stress: Connecting Rod");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "mbd.misesGp");
    model.result("pg5").feature("surf1").set("descr", "von Mises stress");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("RPM");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"N"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"RPM of crankshaft"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "theta/(2*pi)");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Rotation of crankshaft (cycle)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "RPM of crankshaft");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("Power Generated");
    model.result("pg7").set("ylabel", "Power generated (hp)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"P1"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"Power generated in cylinder 1 (hp)"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"P1", "P2"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"Power generated in cylinder 1 (hp)", "Power generated in cylinder 2 (hp)"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"P1", "P2", "P3"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"Power generated in cylinder 1 (hp)", "Power generated in cylinder 2 (hp)", "Power generated in cylinder 3 (hp)"});
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("markerpos", "interp");
    model.result("pg7").feature("glob1").set("markers", 30);
    model.result("pg7").feature("glob1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("ymax", 200);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");
    model.result("pg8").run();
    model.result("pg8").label("Brake Horse Power");
    model.result("pg8").set("ylabel", "BHP");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{"BHP"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"Brake horse power"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg8").run();
    model.result("pg6").run();
    model.result().duplicate("pg9", "pg6");
    model.result("pg9").run();
    model.result("pg9").label("Max Stress: Connecting Rod");
    model.result("pg9").set("ylabel", "von Mises stress (MPa)");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("expr", new String[]{"MaxStress_cr"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"Maximum stress in connecting rod"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"N/m^2"});
    model.result("pg9").feature("glob1").setIndex("unit", "MPa", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "Maximum stress in connecting rod", 0);
    model.result("pg9").feature("glob1").set("titletype", "manual");
    model.result("pg9").feature("glob1").set("title", "Maximum stress in connecting rod");
    model.result("pg9").run();
    model.result("pg7").run();
    model.result().duplicate("pg10", "pg7");
    model.result("pg10").run();
    model.result("pg10").label("Joint Force: Connecting Rod-Crank");
    model.result("pg10").set("ylabel", "Joint force (N)");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"Joint force, x-component"});
    model.result("pg10").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg10").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx", "mbd.hgj2.Fy"});
    model.result("pg10").feature("glob1")
         .set("descr", new String[]{"Joint force, x-component", "Joint force, y-component"});
    model.result("pg10").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx", "mbd.hgj2.Fy", "mbd.hgj2.Fz"});
    model.result("pg10").feature("glob1")
         .set("descr", new String[]{"Joint force, x-component", "Joint force, y-component", "Joint force, z-component"});
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").set("ymin", -5000);
    model.result("pg10").set("ymax", 35000);
    model.result("pg10").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("maxframes", 100);

    model.component("comp1").physics("ht").feature("fluid1").feature("pw1").active(false);

    model.nodeGroup("grp1").active(true);

    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u4e09\u7f38\u5f80\u590d\u5f0f\u53d1\u52a8\u673a");

    model
         .description("\u672c\u4f8b\u9610\u91ca\u4e09\u7f38\u5f80\u590d\u5f0f\u53d1\u52a8\u673a\u7684\u5efa\u6a21\u3002\u5728\u6a21\u578b\u7684\u7b2c\u4e00\u90e8\u5206\uff0c\u4f7f\u7528\u70ed\u529b\u5b66\u5206\u6790\u8ba1\u7b97\u4e86\u6c14\u7f38\u538b\u529b\u968f\u66f2\u8f74\u65cb\u8f6c\u7684\u53d8\u5316\u60c5\u51b5\u3002\u5728\u6a21\u578b\u7684\u7b2c\u4e8c\u90e8\u5206\uff0c\u4f7f\u7528\u4e4b\u524d\u751f\u6210\u7684\u538b\u529b\u6570\u636e\u6267\u884c\u67d4\u6027\u591a\u4f53\u52a8\u529b\u5b66\u5206\u6790\uff0c\u5f97\u5230\u66f2\u8f74\u7684\u8f6c\u901f\u3001\u53d1\u52a8\u673a\u7684\u529f\u7387\u8f93\u51fa\u4ee5\u53ca\u67d4\u6027\u8fde\u6746\u4e2d\u7684\u5e94\u529b\u3002\u9664\u4e86\u4e00\u4e2a\u8fde\u6746\uff0c\u53d1\u52a8\u673a\u4e2d\u7684\u6240\u6709\u5176\u4ed6\u90e8\u4ef6\u90fd\u5047\u5b9a\u4e3a\u521a\u6027\u3002\u4e0d\u540c\u90e8\u4ef6\u4e4b\u95f4\u7684\u8fde\u63a5\u901a\u8fc7\u68f1\u67f1\u5173\u8282\u548c\u94f0\u94fe\u5173\u8282\u5efa\u6a21\u3002");

    model.label("reciprocating_engine.mph");

    model.result("pg3").run();

    model.component("comp1").physics("ht").feature("fluid1").feature("pw1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.component("comp1").physics("ht").feature("fluid1").feature("pw1").active(false);

    model.result("pg3").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u5e94\u529b\u5386\u53f2\uff1a\u8fde\u6746");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(834);
    model.result("pg11").feature("ptgr1").set("expr", "mbd.sp1");
    model.result("pg11").feature("ptgr1").set("unit", "MPa");
    model.result("pg11").feature("ptgr1").set("xdata", "expr");
    model.result("pg11").feature("ptgr1").set("xdataexpr", "theta/(2*pi)");
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u7b2c\u4e00\u4e3b\u5e94\u529b", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr2").set("expr", "mbd.sp2");
    model.result("pg11").feature("ptgr2").setIndex("legends", "Second principal stress", 0);
    model.result("pg11").feature("ptgr2").setIndex("legends", "\u7b2c\u4e8c\u4e3b\u5e94\u529b", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr3").set("expr", "mbd.sp3");
    model.result("pg11").feature("ptgr3").setIndex("legends", "\u7b2c\u4e09\u4e3b\u5e94\u529b", 0);
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr4").set("expr", "mbd.mises");
    model.result("pg11").feature("ptgr4").setIndex("legends", "von Mises \u5e94\u529b", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "\u66f2\u8f74\u65cb\u8f6c\uff08\u5faa\u73af\uff09");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "\u8fde\u6746\u5706\u89d2\u4e2d\u7684\u5e94\u529b\u5386\u53f2");
    model.result("pg11").set("legendpos", "lowerleft");
    model.result("pg11").run();

    model.component("comp2").physics().create("ftg", "Fatigue", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/ftg", false);
    model.study("std2").feature("time").setSolveFor("/physics/ftg", false);

    model.component("comp2").physics("ftg").create("slif1", "StressLifeModel", 2);

    model.component("comp2").selection().create("sel2", "Explicit");
    model.component("comp2").selection("sel2").label("\u4e2d\u95f4\u8fde\u6746\u7684\u8fb9\u754c");
    model.component("comp2").selection("sel2").set(6);
    model.component("comp2").selection("sel2").geom("geom2", 3, 2, new String[]{"exterior"});
    model.component("comp2").selection("sel2").set(6);

    model.component("comp2").physics("ftg").feature("slif1").selection().named("sel2");
    model.component("comp2").physics("ftg").feature("slif1").set("ftgLimit", "Basquin");
    model.component("comp2").physics("ftg").feature("slif1").set("fatigueInputPhysics", "mbd");
    model.component("comp2").physics("ftg").feature("slif1").set("sigmaf_Basquin_mat", "userdef");
    model.component("comp2").physics("ftg").feature("slif1").set("sigmaf_Basquin", "1.043e9");
    model.component("comp2").physics("ftg").feature("slif1").set("b_Basquin_mat", "userdef");
    model.component("comp2").physics("ftg").feature("slif1").set("b_Basquin", -0.116);
    model.component("comp2").physics("ftg").feature("slif1").set("Ncut", "1e20");

    model.study().create("std3");
    model.study("std3").create("ftge", "Fatigue");
    model.study("std3").feature("ftge").set("ftplistmethod", "manual");
    model.study("std3").feature("ftge").set("geometricNonlinearity", false);
    model.study("std3").feature("ftge").set("solnum", "auto");
    model.study("std3").feature("ftge").set("usesol", "off");
    model.study("std3").feature("ftge").set("outputmap", new String[]{});
    model.study("std3").feature("ftge").setSolveFor("/physics/ht", true);
    model.study("std3").feature("ftge").setSolveFor("/physics/c", true);
    model.study("std3").feature("ftge").setSolveFor("/physics/mbd", true);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("ftge").set("geometricNonlinearity", true);
    model.study("std3").feature("ftge").set("usesol", true);
    model.study("std3").feature("ftge").set("notsolmethod", "sol");
    model.study("std3").feature("ftge").set("notstudy", "std2");
    model.study("std3").feature("ftge").set("notsolnum", "from_list");
    model.study("std3").feature("ftge")
         .set("notlistsolnum", new int[]{343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401});
    model.study("std3").label("\u7814\u7a76\uff1a\u75b2\u52b3\u5206\u6790");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset7");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").feature("surf1").set("colortabletrans", "none");
    model.result("pg12").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg12").feature("surf1").set("colortablerev", true);
    model.result("pg12").feature("surf1").set("colortable", "Traffic");
    model.result("pg12").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg12").feature("surf1").create("mrkr1", "Marker");
    model.result("pg12").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg12").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").feature("surf1").feature("mrkr1").active(false);
    model.result("pg12").feature("surf1").feature("mrkr1").active(true);
    model.result("pg12").run();

    model.title("\u5f80\u590d\u5f0f\u6d3b\u585e\u53d1\u52a8\u673a\u7684\u9ad8\u5468\u75b2\u52b3");

    model
         .description("\u5728\u5f80\u590d\u5f0f\u6d3b\u585e\u53d1\u52a8\u673a\u4e2d\uff0c\u8fde\u6746\u53ef\u4ee5\u5c06\u65cb\u8f6c\u8fd0\u52a8\u8f6c\u6362\u4e3a\u5f80\u590d\u8fd0\u52a8\u3002\u8fde\u6746\u662f\u5176\u4e2d\u7684\u5173\u952e\u90e8\u4ef6\uff0c\u9700\u4ece\u75b2\u52b3\u89d2\u5ea6\u8fdb\u884c\u5206\u6790\uff0c\u5e76\u4f7f\u7528 Basquin \u9ad8\u5468\u75b2\u52b3\u51c6\u5219\u6765\u9884\u6d4b\u75b2\u52b3\u5bff\u547d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("engine_fatigue.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
