/*
 * naca0012_airfoil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class naca0012_airfoil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ipf", "IncompressiblePotentialFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ipf", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowSST", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").run();

    model.param().set("U_inf", "50[m*s^-1]");
    model.param().descr("U_inf", "\u81ea\u7531\u6d41\u901f\u5ea6");
    model.param().set("rho_inf", "1.2043[kg*m^-3]");
    model.param().descr("rho_inf", "\u81ea\u7531\u6d41\u5bc6\u5ea6");
    model.param().set("mu_inf", "1.81397e-5[kg*m^-1*s^-1]");
    model.param().descr("mu_inf", "\u81ea\u7531\u6d41\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("L", "180[m]");
    model.param().descr("L", "\u57df\u53c2\u8003\u957f\u5ea6");
    model.param().set("c", "1.8[m]");
    model.param().descr("c", "\u5f26\u957f");
    model.param().set("k_inf", "0.1*mu_inf*U_inf/(rho_inf*L)");
    model.param().descr("k_inf", "\u81ea\u7531\u6d41\u6e4d\u6d41\u52a8\u80fd");
    model.param().set("om_inf", "10*U_inf/L");
    model.param().descr("om_inf", "\u81ea\u7531\u6d41\u6bd4\u8017\u6563\u7387");
    model.param().set("alpha", "0");
    model.param().descr("alpha", "\u653b\u89d2");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "L");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1")
         .set("coord", new String[]{"c*s", "c*0.594689181*(0.298222773*sqrt(s)-0.127125232*s-0.357907906*s^2+0.291984971*s^3-0.105174696*s^4)"});
    model.component("comp1").geom("geom1").feature("pc1").set("pos", new String[]{"-c", "0"});
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "pc1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("del1", "r1");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 1, 2, 4, 5);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argders", new String[][]{{"pA", "d(pA*0.02897/R_const/T,pA)"}, {"T", "d(pA*0.02897/R_const/T,T)"}});
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
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("dermethod", "manual");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic ");
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
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "(def.gamma+1)/2");
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

    model.component("comp1").physics("ipf").prop("PressureProperty").set("UScale", "U_inf");
    model.component("comp1").physics("ipf").create("velbc1", "Velocity", 1);
    model.component("comp1").physics("ipf").feature("velbc1").selection().set(1);
    model.component("comp1").physics("ipf").feature("velbc1")
         .set("Uin", "-nx*U_inf*cos(alpha*pi/180)-ny*U_inf*sin(alpha*pi/180)");
    model.component("comp1").physics("ipf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("ipf").feature("open1").selection().set(2);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").feature("fp1").set("LengthScaleSpecification", "Manual");
    model.component("comp1").physics("spf").feature("fp1").set("lref", 0.2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf").feature("inl1").set("k0", "k_inf");
    model.component("comp1").physics("spf").feature("inl1").set("om0", "om_inf");
    model.component("comp1").physics("spf").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl1")
         .set("u0", new String[]{"U_inf*cos(alpha*pi/180)", "U_inf*sin(alpha*pi/180)", "0"});
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"ipf.u", "ipf.v", "0"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "ipf.p");
    model.component("comp1").physics("spf").feature("init1").set("k_init", "k_inf");
    model.component("comp1").physics("spf").feature("init1").set("om_init", "om_inf");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(2);
    model.component("comp1").physics("spf").feature("open1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf").feature("open1").set("k0", "k_inf");
    model.component("comp1").physics("spf").feature("open1").set("om0", "om_inf");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 15000000);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 480000);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 100);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("elemratio", 15000000);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemcount", 256);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("elemratio", 256);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map3").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map3").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("elemratio", 15000000);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map3").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("elemcount", 25);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("elemratio", 25);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map3").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("map3").feature("dis3").set("numelem", 100);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u57fa\u4e8e\u52bf\u6d41\u89e3\u7684\u901f\u5ea6 (ipf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").view("view1").axis().set("xmin", -2.5);
    model.component("comp1").view("view1").axis().set("xmax", 0.5);
    model.component("comp1").view("view1").axis().set("ymin", -1.1);
    model.component("comp1").view("view1").axis().set("ymax", 1.1);

    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("startmethod", "coord");
    model.result("pg1").feature("str1").set("xcoord", 0);
    model.result("pg1").feature("str1").set("ycoord", "range(-2,0.025,2)");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u52bf\u6d41\u89e3\u7684\u901f\u5ea6\u5927\u5c0f\u548c\u6d41\u7ebf");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("wdi", "WallDistanceInitialization");
    model.study("std2").feature("wdi").set("plotgroup", "Default");
    model.study("std2").feature("wdi").set("solnum", "auto");
    model.study("std2").feature("wdi").set("notsolnum", "auto");
    model.study("std2").feature("wdi").set("outputmap", new String[]{});
    model.study("std2").feature("wdi").set("ngenAUX", "1");
    model.study("std2").feature("wdi").set("goalngenAUX", "1");
    model.study("std2").feature("wdi").set("ngenAUX", "1");
    model.study("std2").feature("wdi").set("goalngenAUX", "1");
    model.study("std2").feature("wdi").setSolveFor("/physics/ipf", true);
    model.study("std2").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/ipf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("wdi").set("usesol", true);
    model.study("std2").feature("wdi").set("notsolmethod", "sol");
    model.study("std2").feature("wdi").set("notstudy", "std1");
    model.study("std2").feature("stat").setSolveFor("/physics/ipf", false);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "U_inf", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std2").feature("stat").setIndex("pname", "U_inf", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m/s", 0);
    model.study("std2").feature("stat").setIndex("pname", "alpha", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "0,2,4,6,8,10,12,14", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 8, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 8, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset2");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(3, 4);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(3, 4);
    model.result().numerical("int1")
         .setIndex("expr", "p/(1/2*rho_inf*U_inf^2)/c*(spf.nymesh*cos(alpha*pi/180)-spf.nxmesh*sin(alpha*pi/180))", 0);
    model.result().numerical("int1").set("data", "dset2");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").run();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("naca0012_airfoil_Ladson_CL.dat");
    model.result("pg5").run();
    model.result("pg5").create("tblp2", "Table");
    model.result("pg5").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp2").set("linewidth", "preference");
    model.result("pg5").feature("tblp2").set("table", "tbl2");
    model.result("pg5").feature("tblp2").set("linestyle", "none");
    model.result("pg5").feature("tblp2").set("linecolor", "blue");
    model.result("pg5").feature("tblp2").set("linemarker", "point");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5347\u529b vs. \u653b\u89d2");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\\alpha");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "CL");
    model.result("pg5").run();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").importData("naca0012_airfoil_Gregory_OReilly_Cp.dat");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "table");
    model.result("pg6").feature("tblp1").set("table", "tbl3");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("linestyle", "none");
    model.result("pg6").feature("tblp1").set("linecolor", "blue");
    model.result("pg6").feature("tblp1").set("linemarker", "point");
    model.result("pg6").run();
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "dset2");
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("lngr1").setIndex("looplevel", new int[]{6}, 0);
    model.result("pg6").feature("lngr1").selection().set(3, 4);
    model.result("pg6").feature("lngr1").set("expr", "-p/(1/2*rho_inf*U_inf^2)");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "(x+c)/c");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "(x-xLE)/c");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "-cp");
    model.result("pg6").run();
    model.result("pg2").run();
    model.result("pg2").set("view", "view1");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("startmethod", "coord");
    model.result("pg2").feature("str1").set("xcoord", 0);
    model.result("pg2").feature("str1").set("ycoord", "range(-2,0.025,2)");
    model.result("pg2").run();

    model.title("NACA 0012 \u7ffc\u578b\u5468\u56f4\u7684\u6d41\u573a");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 SST \u6e4d\u6d41\u6a21\u578b\u6a21\u62df\u4e0d\u540c\u653b\u89d2\u65f6 NACA 0012 \u7ffc\u578b\u5468\u56f4\u7684\u6d41\u573a\u3002\u7ed3\u679c\u4e0e Ladson \u7684\u5347\u529b\u6570\u636e\u4ee5\u53ca Gregory \u548c O'Reilly \u7684\u538b\u529b\u6570\u636e\u7b49\u8bd5\u9a8c\u6570\u636e\u5f88\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("naca0012_airfoil.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
