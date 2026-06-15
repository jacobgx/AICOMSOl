/*
 * vacuum_flask.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:34 by COMSOL 6.3.0.290. */
public class vacuum_flask {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("vacuum_flask_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u58f3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(9, 10, 11, 12, 14, 17, 18, 19, 20, 21, 22);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4fdd\u6e29\u74f6\uff0c\u5782\u76f4\u58c1");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(15, 16, 17, 22);

    model.param().set("T_amb", "25[degC]");
    model.param().descr("T_amb", "\u5468\u56f4\u7a7a\u6c14\u7684\u6e29\u5ea6");
    model.param().set("T_coffee", "90[degC]");
    model.param().descr("T_coffee", "\u5496\u5561\u6e29\u5ea6");
    model.param().set("d_shell", "0.5[mm]");
    model.param().descr("d_shell", "\u94a2\u58f3\u539a\u5ea6");
    model.param().set("p_amb", "1[atm]");
    model.param().descr("p_amb", "\u73af\u5883\u538b\u529b");

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
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Nylon");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("lighting", "phong");
    model.component("comp1").material("mat3").set("shininess", 500);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Steel AISI 4340");
    model.component("comp1").material("mat4").set("family", "steel");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat3").selection().set(4, 5);
    model.component("comp1").material("mat4").selection().geom("geom1", 1);
    model.component("comp1").material("mat4").selection().named("sel1");
    model.component("comp1").material("mat4").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat4").propertyGroup("shell").set("lth", new String[]{"d_shell"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u6ce1\u6cab");
    model.component("comp1").material("mat5").selection().set(1);
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.03[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"60[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"200[J/(kg*K)]"});

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "T_amb");

    model.component("comp1").physics("ht").create("fluid1", "FluidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("fluid1").selection().set(3);
    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("id1", "IsothermalDomain", 2);
    model.component("comp1").physics("ht").feature("id1").selection().set(2);
    model.component("comp1").physics("ht").feature("idi1").set("InterfaceType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("idi1").set("h", "100[W/(m^2*K)]");
    model.component("comp1").physics("ht").create("init2", "init", 2);
    model.component("comp1").physics("ht").feature("init2").selection().set(2);
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T_coffee");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 1);
    model.component("comp1").physics("ht").feature("sls1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "Conductive");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("HeatTransferCoefficientType", "ExtNaturalConvection");
    model.component("comp1").physics("ht").feature("hf1").set("Lwall", "height");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(8);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("HeatTransferCoefficientType", "ExtNaturalConvection");
    model.component("comp1").physics("ht").feature("hf2").set("ExtNaturalConvectionType", "HorizontalPlateUp");
    model.component("comp1").physics("ht").feature("hf2").set("Ldia", "radius");
    model.component("comp1").physics("ht").feature("hf2").set("Text_src", "root.comp1.ampr1.T_amb");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 1);
    model.result().dataset("lshl1").selection().set(9, 10, 11, 12, 14, 17, 18, 19, 20, 21, 22);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().set(1, 2, 3, 4, 5);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u57df");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("surf2", "Surface");
    model.result("pg1").feature("surf2").label("\u591a\u5c42\u58f3");
    model.result("pg1").feature("surf2").set("data", "lshl1");
    model.result("pg1").feature("surf2").setIndex("looplevel", 101, 0);
    model.result("pg1").feature("surf2").set("solutionparams", "parent");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("smooth", "internal");
    model.result("pg1").feature("surf2").set("data", "lshl1");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result("pg1").feature("line1").setIndex("looplevel", 101, 0);
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("data", "lshl1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht"});
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "lshl1");
    model.result().dataset("rev2").selection().geom("geom1", 2);
    model.result().dataset("rev2").selection().set(1, 2, 3, 4, 5);
    model.result().dataset("rev2")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht|vol2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht|line1", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht|mslc2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht|line1", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht|iso2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht|line1"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("vol2", "Volume");
    model.result("pg2").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg2").feature("vol2").set("solutionparams", "parent");
    model.result("pg2").feature("vol2").set("titletype", "none");
    model.result("pg2").feature("vol2").set("smooth", "internal");
    model.result("pg2").feature("vol2").set("data", "rev2");
    model.result("pg2").feature("vol2").set("inheritplot", "vol1");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "fromtheme");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("data", "rev2");
    model.result("pg2").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg2").run();
    model.result("pg2").label("\u4e09\u7ef4\u6e29\u5ea6 (ht)");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("ht2", "HeatTransferInSolidsAndFluids", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/ht2", false);

    model.component("comp2").physics("ht2").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/spf", false);

    model.component("comp2").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp2").multiphysics().create("nitf1", "NonIsothermalFlow", 2);

    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", false);

    model.component("comp2").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("nitf1").set("Heat_physics", "ht2");

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ht", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "geom1");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"0.1[m]", "0.5[m]"});
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").selection().create("sel3", "Explicit");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").selection("sel3").label("\u58f3");
    model.component("comp2").selection("sel3").geom(1);
    model.component("comp2").selection("sel3").set(11, 12, 13, 14, 16, 19, 22, 23, 24, 25, 26);
    model.component("comp2").selection().create("sel4", "Explicit");
    model.component("comp2").selection("sel4").label("\u4fdd\u6e29\u74f6\uff0c\u5782\u76f4\u58c1");
    model.component("comp2").selection("sel4").geom(1);
    model.component("comp2").selection("sel4").set(17, 18, 19, 26);

    model.component("comp2").material().create("mat6", "Common");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat6").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat6").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat6").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat6").label("Air");
    model.component("comp2").material("mat6").set("family", "air");
    model.component("comp2").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat6").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat6").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat6").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat6").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat6").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat6").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat6").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat6").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat6").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat6").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat6").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat6").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat6").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat6").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat6").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat6").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat6").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat6").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat6").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat6").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat6").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat6").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat6").materialType("nonSolid");
    model.component("comp2").material().create("mat7", "Common");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat7").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp2").material("mat7").label("Water, liquid");
    model.component("comp2").material("mat7").set("family", "water");
    model.component("comp2").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat7").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat7").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat7").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat7").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat7").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat7").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat7").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat7").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat7").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp2").material("mat7").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp2").material("mat7").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp2").material("mat7").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp2").material("mat7").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat7").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp2").material("mat7").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat7").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat7").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat7").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp2").material("mat7").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp2").material("mat7").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp2").material("mat7").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat7").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp2").material("mat7").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp2").material("mat7").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp2").material("mat7").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp2").material("mat7").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp2").material("mat7").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat7").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp2").material("mat7").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat7").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp2").material("mat7").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat7").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat7").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp2").material("mat7").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat7").propertyGroup("def").set("density", "rho(T)");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat7").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat7").propertyGroup("def").addInput("temperature");
    model.component("comp2").material().create("mat8", "Common");
    model.component("comp2").material("mat8").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat8").label("Nylon");
    model.component("comp2").material("mat8").set("family", "custom");
    model.component("comp2").material("mat8")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat8").set("diffuse", "custom");
    model.component("comp2").material("mat8")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp2").material("mat8").set("ambient", "custom");
    model.component("comp2").material("mat8")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp2").material("mat8").set("noise", true);
    model.component("comp2").material("mat8").set("lighting", "phong");
    model.component("comp2").material("mat8").set("shininess", 500);
    model.component("comp2").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat8").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp2").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp2").material("mat8").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp2").material("mat8").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp2").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp2").material("mat8").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat8").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp2").material("mat8").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp2").material().create("mat9", "Common");
    model.component("comp2").material("mat9").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat9").label("Steel AISI 4340");
    model.component("comp2").material("mat9").set("family", "steel");
    model.component("comp2").material("mat9").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat9").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat9").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat9").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat9").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp2").material("mat9").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp2").material("mat7").selection().set(2);
    model.component("comp2").material("mat8").selection().set(4, 6);
    model.component("comp2").material("mat9").selection().geom("geom2", 1);
    model.component("comp2").material("mat9").selection().named("sel3");
    model.component("comp2").material("mat9").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp2").material("mat9").propertyGroup("shell").set("lth", new String[]{"d_shell"});
    model.component("comp2").material().create("mat10", "Common");
    model.component("comp2").material("mat10").label("\u6ce1\u6cab");
    model.component("comp2").material("mat10").selection().set(1);
    model.component("comp2").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.03[W/(m*K)]"});
    model.component("comp2").material("mat10").propertyGroup("def").set("density", new String[]{"60[kg/m^3]"});
    model.component("comp2").material("mat10").propertyGroup("def")
         .set("heatcapacity", new String[]{"200[J/(kg*K)]"});

    model.component("comp2").physics("spf").selection().set(5);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);

    model.component("comp2").selection().create("sel5", "Explicit");
    model.component("comp2").selection("sel5").geom(2);
    model.component("comp2").selection("sel5").label("\u7a7a\u6c14");
    model.component("comp2").selection("sel5").set(5);

    model.component("comp2").physics("spf").selection().named("sel5");

    model.component("comp2").common().create("ampr2", "AmbientProperties");
    model.component("comp2").common("ampr2").set("T_amb", "T_amb");

    model.component("comp2").physics("ht2").prop("PhysicalModelProperty").set("Tref", "T_amb");
    model.component("comp2").physics("ht2").feature("fluid1").selection().named("sel5");
    model.component("comp2").physics("ht2").feature("init1").set("Tinit_src", "root.comp2.ampr2.T_amb");
    model.component("comp2").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp2").physics("spf").feature("open1").selection().set(10, 21);
    model.component("comp2").physics("ht2").create("fluid2", "FluidHeatTransferModel", 2);
    model.component("comp2").physics("ht2").feature("fluid2").selection().set(3);
    model.component("comp2").physics("ht2").create("id1", "IsothermalDomain", 2);
    model.component("comp2").physics("ht2").feature("id1").selection().set(2);
    model.component("comp2").physics("ht2").feature("idi1").set("InterfaceType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("idi1").set("h", "100[W/(m^2*K)]");
    model.component("comp2").physics("ht2").create("init2", "init", 2);
    model.component("comp2").physics("ht2").feature("init2").selection().set(2);
    model.component("comp2").physics("ht2").feature("init2").set("Tinit", "T_coffee");
    model.component("comp2").physics("ht2").create("sls1", "SolidLayeredShell", 1);
    model.component("comp2").physics("ht2").feature("sls1").selection().named("sel3");
    model.component("comp2").physics("ht2").feature("sls1").set("LayerType", "Conductive");
    model.component("comp2").physics("ht2").create("open1", "OpenBoundary", 1);
    model.component("comp2").physics("ht2").feature("open1").selection().set(10, 21);
    model.component("comp2").physics("ht2").feature("open1").set("Tustr", "T_amb");

    model.component("comp2").mesh("mesh2").autoMeshSize(3);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,10)[s] range(0.1,0.1,10)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("data", "dset3");
    model.result().dataset("lshl2").selection().geom("geom2", 1);
    model.result().dataset("lshl2").selection().set(11, 12, 13, 14, 16, 19, 22, 23, 24, 25, 26);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht2)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 201, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").selection().geom("geom2", 2);
    model.result("pg3").selection().set(1, 2, 3, 4, 5, 6);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u57df");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("surf2", "Surface");
    model.result("pg3").feature("surf2").label("\u591a\u5c42\u58f3");
    model.result("pg3").feature("surf2").set("data", "lshl2");
    model.result("pg3").feature("surf2").setIndex("looplevel", 201, 0);
    model.result("pg3").feature("surf2").set("solutionparams", "parent");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("smooth", "internal");
    model.result("pg3").feature("surf2").set("data", "lshl2");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg3").feature("line1").set("data", "lshl2");
    model.result("pg3").feature("line1").setIndex("looplevel", 201, 0);
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("data", "lshl2");
    model.result().dataset("dset3").set("geom", "geom2");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 201, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.U");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 201, 0);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev3").set("data", "none");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 225);
    model.result().dataset("rev3").set("data", "dset3");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg6").set("data", "rev3");
    model.result("pg6").setIndex("looplevel", 201, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 201, 0);
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "nitf1.T");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom2", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection().set(5);
    model.result("pg7").feature().create("surf2", "Surface");
    model.result("pg7").feature("surf2").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("solutionparams", "parent");
    model.result("pg7").feature("surf2").set("expr", "nitf1.T");
    model.result("pg7").feature("surf2").set("smooth", "internal");
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("data", "parent");
    model.result("pg7").feature("surf2").feature().create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().geom("geom2", 2);
    model.result("pg7").feature("surf2").feature("sel1").selection().set(1, 4, 6);
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature().create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("solutionparams", "parent");
    model.result("pg7").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg7").feature("arws1").set("xnumber", 30);
    model.result("pg7").feature("arws1").set("ynumber", 30);
    model.result("pg7").feature("arws1").set("arrowtype", "cone");
    model.result("pg7").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("data", "parent");
    model.result("pg7").feature("arws1").feature().create("col1", "Color");
    model.result("pg7").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg7").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg7").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg7").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u7814\u7a76 1");

    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").label("\u7814\u7a76 2");

    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").set("startangle", -90);
    model.result().dataset("rev4").set("revangle", 225);
    model.result().dataset("rev4").set("data", "dset3");
    model.result().dataset("rev4")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht2"});
    model.result().dataset().create("rev5", "Revolve2D");
    model.result().dataset("rev5").set("startangle", -90);
    model.result().dataset("rev5").set("revangle", 225);
    model.result().dataset("rev5").set("data", "lshl2");
    model.result().dataset("rev5").selection().geom("geom2", 2);
    model.result().dataset("rev5").selection().set(1, 2, 3, 4, 5, 6);
    model.result().dataset("rev5")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht2|vol2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg2|ht2|line1", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht2|mslc2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg3|ht2|line1", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht2|iso2", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond3/pcond1/pcond2/pg4|ht2|line1"});
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u6e29\u5ea6 (ht2) 1");
    model.result("pg8").set("data", "rev4");
    model.result("pg8").setIndex("looplevel", 201, 0);
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").label("\u57df");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result("pg8").feature().create("vol2", "Volume");
    model.result("pg8").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg8").feature("vol2").set("solutionparams", "parent");
    model.result("pg8").feature("vol2").set("titletype", "none");
    model.result("pg8").feature("vol2").set("smooth", "internal");
    model.result("pg8").feature("vol2").set("data", "rev5");
    model.result("pg8").feature("vol2").set("inheritplot", "vol1");
    model.result("pg8").feature().create("line1", "Line");
    model.result("pg8").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg8").feature("line1").set("solutionparams", "parent");
    model.result("pg8").feature("line1").set("expr", "1");
    model.result("pg8").feature("line1").set("titletype", "none");
    model.result("pg8").feature("line1").set("coloring", "uniform");
    model.result("pg8").feature("line1").set("color", "fromtheme");
    model.result("pg8").feature("line1").set("smooth", "internal");
    model.result("pg8").feature("line1").set("data", "rev5");
    model.result("pg8").label("\u6e29\u5ea6 (ht2) 1");

    model.nodeGroup("grp2").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").label("\u4e09\u7ef4\u6e29\u5ea6 (ht2)");
    model.result("pg6").run();
    model.result().create("pg9", "PlotGroup2D");

    model.nodeGroup("grp2").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("\u901f\u5ea6\uff0c\u6d41\u7ebf");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 201, 0);
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"u", "w"});
    model.result("pg9").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg9").feature("str1").set("posmethod", "magnitude");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").create("col1", "Color");
    model.result("pg9").run();
    model.result("pg9").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg9").feature("str1").feature("col1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u5496\u5561\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u5496\u5561\u6e29\u5ea6\u6f14\u53d8");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u6e29\u5ea6 (\u00b0C)");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").set("expr", new String[]{"ht.id1.T"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"\u7b49\u6e29\u57df\u6e29\u5ea6"});
    model.result("pg10").feature("glob1").set("unit", new String[]{"\u00b0C"});
    model.result("pg10").feature("glob1")
         .setIndex("descr", "\u7b49\u6e29\u57df\u6e29\u5ea6 - \u4e0d\u8ba1\u7b97\u6d41\u52a8", 0);
    model.result("pg10").feature("glob1").set("linemarker", "cycle");
    model.result("pg10").feature("glob1").set("legendmethod", "manual");
    model.result("pg10").feature("glob1").setIndex("legends", "\u4e0d\u8ba1\u7b97\u6d41\u52a8", 0);
    model.result("pg10").run();
    model.result("pg10").create("glob2", "Global");
    model.result("pg10").feature("glob2").set("markerpos", "datapoints");
    model.result("pg10").feature("glob2").set("linewidth", "preference");
    model.result("pg10").feature("glob2").set("data", "dset3");
    model.result("pg10").feature("glob2").set("expr", new String[]{"ht2.id1.T"});
    model.result("pg10").feature("glob2").set("descr", new String[]{"\u7b49\u6e29\u57df\u6e29\u5ea6"});
    model.result("pg10").feature("glob2").set("unit", new String[]{"\u00b0C"});
    model.result("pg10").feature("glob2")
         .setIndex("descr", "\u7b49\u6e29\u57df\u6e29\u5ea6 - \u8ba1\u7b97\u6d41\u52a8", 0);
    model.result("pg10").feature("glob2").set("linestyle", "none");
    model.result("pg10").feature("glob2").set("linemarker", "cycle");
    model.result("pg10").feature("glob2").set("legendmethod", "manual");
    model.result("pg10").feature("glob2").setIndex("legends", "\u8ba1\u7b97\u6d41\u52a8", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u4f20\u70ed\u7cfb\u6570");
    model.result("pg11").setIndex("looplevelinput", "last", 0);
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "\u4f20\u70ed\u7cfb\u6570\u6bd4\u8f83");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u4f20\u70ed\u7cfb\u6570 (W/(m^2*K))");
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").selection().named("sel2");
    model.result("pg11").feature("lngr1").set("expr", "ht.hf1.h");
    model.result("pg11").feature("lngr1").set("xdata", "expr");
    model.result("pg11").feature("lngr1").set("xdataexpr", "z");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "manual");
    model.result("pg11").feature("lngr1")
         .setIndex("legends", "\u4f30\u7b97\u7ed3\u679c\uff08\u4e0d\u8ba1\u7b97\u6d41\u52a8\uff09", 0);
    model.result("pg11").run();
    model.result("pg11").create("lngr2", "LineGraph");
    model.result("pg11").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr2").set("linewidth", "preference");
    model.result("pg11").feature("lngr2").set("data", "dset3");
    model.result("pg11").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg11").feature("lngr2").selection().named("sel4");
    model.result("pg11").feature("lngr2").set("expr", "abs(ht2.ntflux)/(T2-T_amb)");
    model.result("pg11").feature("lngr2").set("xdata", "expr");
    model.result("pg11").feature("lngr2").set("xdataexpr", "z");
    model.result("pg11").feature("lngr2").set("legend", true);
    model.result("pg11").feature("lngr2").set("legendmethod", "manual");
    model.result("pg11").feature("lngr2")
         .setIndex("legends", "\u8ba1\u7b97\u7ed3\u679c\uff08\u8ba1\u7b97\u6d41\u52a8\uff09", 0);
    model.result("pg11").run();
    model.result("pg11").run();

    model.title("\u4fdd\u6e29\u74f6\u7684\u81ea\u7136\u5bf9\u6d41\u51b7\u5374");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u4fdd\u6e29\u74f6\u5728\u5355\u4f4d\u65f6\u95f4\u5185\u70ed\u6d41\u4f53\u7684\u70ed\u91cf\u8017\u6563\u3002\u901a\u8fc7\u4e24\u79cd\u65b9\u6cd5\u6765\u6a21\u62df\u81ea\u7136\u5bf9\u6d41\u51b7\u5374\u3002\u7b2c\u4e00\u79cd\u65b9\u6cd5\u662f\u4f7f\u7528\u4f20\u70ed\u7cfb\u6570\u6765\u63cf\u8ff0\u70ed\u8017\u6563\uff1b\u7b2c\u4e8c\u79cd\u65b9\u6cd5\u662f\u5bf9\u74f6\u5916\u7684\u7a7a\u6c14\u5bf9\u6d41\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("vacuum_flask.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
