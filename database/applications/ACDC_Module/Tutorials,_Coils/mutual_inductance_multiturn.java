/*
 * mutual_inductance_multiturn.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class mutual_inductance_multiturn {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Coils");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.param().set("r_wire", "1[mm]");
    model.param().descr("r_wire", "\u534a\u5f84\uff0c\u5bfc\u7ebf");
    model.param().set("R1", "100[mm]");
    model.param().descr("R1", "\u534a\u5f84\uff0c\u5916\u7ebf\u5708");
    model.param().set("R2", "10[mm]");
    model.param().descr("R2", "\u534a\u5f84\uff0c\u5185\u7ebf\u5708");
    model.param().set("N", "20");
    model.param().descr("N", "\u4e8c\u6b21\u7ebf\u5708\u7684\u531d\u6570");
    model.param().set("M", "N*(mu0_const*pi*R2^2)/(2*R1)");
    model.param().descr("M", "\u4e8c\u6b21\u7ebf\u5708\u7684\u89e3\u6790\u4e92\u611f");
    model.param().set("I1", "1[A]");
    model.param().descr("I1", "\u7535\u6d41\uff0c\u5916\u7ebf\u5708");
    model.param().set("I2", "0[A]");
    model.param().descr("I2", "\u7535\u6d41\uff0c\u5185\u7ebf\u5708");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("r", "1.75*R1");
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "50[mm]", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "r_wire");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"R1", "0"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_wire*5", "r_wire*29"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"R2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "r_wire");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(1, 3);

    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I1");
    model.component("comp1").physics("mf").feature("coil1").selection().set(5);
    model.component("comp1").physics("mf").create("coil2", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil2").selection().set(4);
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I2");
    model.component("comp1").physics("mf").feature("coil2").set("N", "N");
    model.component("comp1").physics("mf").feature("coil2").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil2").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil2").set("coilWindArea", "pi*r_wire^2");

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
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().set(5);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 4, 5);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("startmethod", "coord");
    model.result("pg1").feature("str1").set("xcoord", "range(0,0.9*R1/49,0.9*R1)");
    model.result("pg1").feature("str1").set("ycoord", 0);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "mf.LCoil_1", 0);
    model.result().numerical("gev1").setIndex("unit", "nH", 0);
    model.result().numerical("gev1").setIndex("descr", "\u5916\u7ebf\u5708\u7535\u611f", 0);
    model.result().numerical("gev1").setIndex("expr", "2*mf.intWm/1[A^2]", 1);
    model.result().numerical("gev1").setIndex("unit", "nH", 1);
    model.result().numerical("gev1")
         .setIndex("descr", "\u5916\u7ebf\u5708\u7535\u611f\u7684\u80fd\u91cf\u4f30\u8ba1\u503c", 1);
    model.result().numerical("gev1").setIndex("expr", "mf.L_2_1", 2);
    model.result().numerical("gev1").setIndex("unit", "nH", 2);
    model.result().numerical("gev1").setIndex("descr", "\u8ba1\u7b97\u5f97\u51fa\u7684\u4e92\u611f", 2);
    model.result().numerical("gev1").setIndex("expr", "M", 3);
    model.result().numerical("gev1").setIndex("unit", "nH", 3);
    model.result().numerical("gev1").setIndex("descr", "\u89e3\u6790\u5f97\u51fa\u7684\u4e92\u611f", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.param().set("I1", "0[A]");
    model.param().set("I2", "1[A]");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "mf.LCoil_2", 0);
    model.result().numerical("gev2").setIndex("unit", "nH", 0);
    model.result().numerical("gev2").setIndex("descr", "\u5185\u7ebf\u5708\u7535\u611f", 0);
    model.result().numerical("gev2").setIndex("expr", "2*mf.intWm/1[A^2]", 1);
    model.result().numerical("gev2").setIndex("unit", "nH", 1);
    model.result().numerical("gev2")
         .setIndex("descr", "\u5185\u7ebf\u5708\u7535\u611f\u7684\u80fd\u91cf\u4f30\u8ba1\u503c", 1);
    model.result().numerical("gev2").setIndex("expr", "mf.L_1_2", 2);
    model.result().numerical("gev2").setIndex("unit", "nH", 2);
    model.result().numerical("gev2").setIndex("descr", "\u8ba1\u7b97\u5f97\u51fa\u7684\u4e92\u611f", 2);
    model.result().numerical("gev2").setIndex("expr", "M", 3);
    model.result().numerical("gev2").setIndex("unit", "nH", 3);
    model.result().numerical("gev2").setIndex("descr", "\u89e3\u6790\u5f97\u51fa\u7684\u4e92\u611f", 3);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "R2", 1, 0);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "R1", 1, 0);
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").set("data", "cln1");
    model.result().numerical("int1").setIndex("expr", "20*mf.Bz/I1", 0);
    model.result().numerical("int1").setIndex("unit", "nH", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl3");
    model.result().numerical("int1").setResult();
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").set("data", "cln2");
    model.result().numerical("int2").setIndex("expr", "mf.Bz/I1", 0);
    model.result().numerical("int2").set("table", "tbl3");
    model.result().numerical("int2").appendResult();
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().numerical("int1").setIndex("expr", "20*mf.Bz/I2", 0);
    model.result().numerical("int1").set("table", "tbl3");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2").setIndex("expr", "mf.Bz/I2", 0);
    model.result().numerical("int2").set("table", "tbl3");
    model.result().numerical("int2").appendResult();

    model.param().set("I1", "1[A]");
    model.param().set("I2", "0[A]");

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std3").feature("freq").set("plist", "1[kHz]");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(2, 4, 5);
    model.result("pg1").run();
    model.result("pg1").set("data", "dset3");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "dset3");
    model.result().numerical("gev3").setIndex("expr", "mf.VCoil_2/1[A]/mf.iomega", 0);
    model.result().numerical("gev3").setIndex("unit", "nH", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl4");
    model.result().numerical("gev3").setResult();

    model.component("comp1").physics("mf").feature("coil2").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mf").feature("coil2").set("VCoil", 0);

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std4").feature("freq").set("plist", "1[kHz]");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(2, 4, 5);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("startmethod", "coord");
    model.result("pg2").feature("str1").set("xcoord", "range(0,0.9*R1/49,0.9*R1)");
    model.result("pg2").feature("str1").set("ycoord", 0);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").set("data", "dset4");
    model.result().numerical("gev4").setIndex("expr", "mf.ICoil_2", 0);
    model.result().numerical("gev4")
         .setIndex("expr", "-mf.iomega*withsol('sol1',mf.L_2_1)/(withsol('sol2',mf.RCoil_2)+withsol('sol2',mf.LCoil_2)*mf.iomega)*mf.ICoil_1", 1);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5168\u5c40\u8ba1\u7b97 4");
    model.result().numerical("gev4").set("table", "tbl5");
    model.result().numerical("gev4").setResult();

    model.title("\u5355\u5bfc\u7ebf\u548c\u5747\u5300\u87ba\u65cb\u7ebf\u5708\u7684\u81ea\u611f\u548c\u4e92\u611f");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u9891\u57df\u6a21\u578b\u8ba1\u7b97\u540c\u5fc3\u5171\u9762\u5e03\u7f6e\u4e2d\u5355\u531d\u521d\u7ea7\u7ebf\u5708\u548c\u4e8c\u5341\u531d\u6b21\u7ea7\u7ebf\u5708\u4e4b\u95f4\u7684\u4e92\u611f\u548c\u611f\u5e94\u7535\u6d41\u3002\u5176\u4e2d\u91c7\u7528\u5747\u8d28\u65b9\u6cd5\u5bf9\u6b21\u7ea7\u7ebf\u5708\u8fdb\u884c\u5efa\u6a21\uff0c\u8fd9\u79cd\u65b9\u6cd5\u4e0d\u660e\u786e\u8003\u8651\u6bcf\u4e00\u531d\u7ebf\u5708\uff1b\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u9884\u6d4b\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("mutual_inductance_multiturn.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
