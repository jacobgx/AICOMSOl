/*
 * airlift_loop_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class airlift_loop_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("bf", "BubblyFlowkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/bf", true);

    model.param().set("H", "1.75[m]");
    model.param().descr("H", "\u53cd\u5e94\u5668\u9ad8\u5ea6");
    model.param().set("W", "0.5[m]");
    model.param().descr("W", "\u53cd\u5e94\u5668\u5bbd\u5ea6");
    model.param().set("T", "0.08[m]");
    model.param().descr("T", "\u53cd\u5e94\u5668\u539a\u5ea6");
    model.param().set("d_b", "3e-3[m]");
    model.param().descr("d_b", "\u6c14\u6ce1\u76f4\u5f84");
    model.param().set("R", "0.02[m]");
    model.param().descr("R", "\u5165\u53e3\u534a\u5f84");
    model.param().set("L", "0.16[m]");
    model.param().descr("L", "\u4e0a\u5347\u901a\u9053\u548c\u4e0b\u964d\u901a\u9053\u7684\u5bbd\u5ea6");
    model.param().set("V_in", "0.015[m/s]");
    model.param().descr("V_in", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("Cw", "5e4[kg/(m^3*s)]");
    model.param().descr("Cw", "\u6ed1\u79fb\u901f\u5ea6\u6bd4\u4f8b\u5e38\u6570");
    model.param().set("rhog_in", "0.9727[kg/(m^3)]");
    model.param().descr("rhog_in", "\u5165\u53e3\u5904\u7684\u6c14\u4f53\u5bc6\u5ea6");

    model.func().create("step1", "Step");
    model.func("step1").set("location", 5);
    model.func("step1").set("smooth", 10);

    model.component("comp1").geom("geom1").insertFile("airlift_loop_reactor_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("bf").feature("fp1").set("Liquid", "mat2");
    model.component("comp1").physics("bf").feature("fp1").set("Gas", "mat1");
    model.component("comp1").physics("bf").feature("fp1").set("rhog_mat", "CalculateFromIdealGasLaw");
    model.component("comp1").physics("bf").feature("fp1").set("diamb", "d_b");
    model.component("comp1").physics("bf").feature("fp1").set("SlipModel", "PressureDragBalance");
    model.component("comp1").physics("bf").feature("fp1").set("DragCoefficientModel", "LargeBubbles");
    model.component("comp1").physics("bf").prop("PhysicalModelProperty").set("LowGasConcentration", false);
    model.component("comp1").physics("bf").prop("TurbulenceModelProperty")
         .set("editTurbulenceModelParameters", true);
    model.component("comp1").physics("bf").prop("TurbulenceModelProperty").set("C_epsbf", 1.4);
    model.component("comp1").physics("bf").prop("TurbulenceModelProperty").set("C_kbf", 0.6);
    model.component("comp1").physics("bf").create("gr1", "Gravity", 3);
    model.component("comp1").physics("bf").feature("gr1").selection().set(1);
    model.component("comp1").physics("bf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("bf").feature("gr1").set("g", new String[]{"0", "-g_const", "0"});
    model.component("comp1").physics("bf").feature("wallbc2").selection().set(6, 7);
    model.component("comp1").physics("bf").feature("wallbc2").set("GasBoundaryCondition", "GasFlux");
    model.component("comp1").physics("bf").feature("wallbc2").set("Nrhogeff", "V_in*rhog_in*step1(t[1/s])");
    model.component("comp1").physics("bf").create("wallbc3", "WallBC", 2);
    model.component("comp1").physics("bf").feature("wallbc3").selection().set(5);
    model.component("comp1").physics("bf").feature("wallbc3").set("LiquidBoundaryCondition", "Slip");
    model.component("comp1").physics("bf").feature("wallbc3").set("GasBoundaryCondition", "GasOutlet");
    model.component("comp1").physics("bf").feature("init1").set("p", "g_const*bf.rhol*(1.75-y)");
    model.component("comp1").physics("bf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("bf").feature("prpc1").selection().set(23);
    model.component("comp1").physics("bf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("bf").feature("sym1").selection().set(4);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,1)*30");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_p").set("scaleval", "1.7e4");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", 0.5);
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6db2\u4f53 (bf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u901f\u5ea6\u5927\u5c0f");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("expr", "bf.Ul");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6c14\u76f8 (bf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u76f8");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (bf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (bf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "bf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection().set(4);
    model.result().dataset("surf2").set("param", "xy");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").run();
    model.result("pg5").set("data", "surf2");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("color", "white");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.025);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("data", "surf2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "bf.muT");
    model.result("pg6").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("vl3");
    model.result().table("tbl1").importData("airlift_loop_reactor_vl_no3.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("vg3");
    model.result().table("tbl2").importData("airlift_loop_reactor_vg_no3.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("vl5");
    model.result().table("tbl3").importData("airlift_loop_reactor_vl_no5.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("vg5");
    model.result().table("tbl4").importData("airlift_loop_reactor_vg_no5.txt");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").label("vl7");
    model.result().table("tbl5").importData("airlift_loop_reactor_vl_no7.txt");
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").label("vg7");
    model.result().table("tbl6").importData("airlift_loop_reactor_vg_no7.txt");
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").label("vl9");
    model.result().table("tbl7").importData("airlift_loop_reactor_vl_no9.txt");
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").label("vg9");
    model.result().table("tbl8").importData("airlift_loop_reactor_vg_no9.txt");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").label("No3");
    model.result().dataset("cln1").setIndex("genpoints", 0.3, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.04, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 0.15, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.3, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.04, 1, 2);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").label("No5");
    model.result().dataset("cln2").setIndex("genpoints", 0.65, 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", 0.65, 1, 1);
    model.result().dataset().duplicate("cln3", "cln2");
    model.result().dataset("cln3").label("No7");
    model.result().dataset("cln3").setIndex("genpoints", 1.25, 0, 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("cln3").setIndex("genpoints", 1.25, 1, 1);
    model.result().dataset().duplicate("cln4", "cln3");
    model.result().dataset("cln4").label("No9");
    model.result().dataset("cln4").setIndex("genpoints", 1.65, 0, 1);
    model.result().dataset("cln4").setIndex("genpoints", 1.65, 1, 1);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u63a2\u9488\u4f4d\u7f6e #3");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u63a2\u9488\u4f4d\u7f6e #3 \u5904\u7684\u6db2\u4f53\u548c\u6c14\u4f53\u5782\u76f4\u901f\u5ea6");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "v");
    model.result("pg7").feature("lngr1").set("linecolor", "blue");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u6d41\u4f53\u901f\u5ea6\uff0c\u4eff\u771f", 0);
    model.result("pg7").run();
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("linestyle", "none");
    model.result("pg7").feature("tblp1").set("linecolor", "blue");
    model.result("pg7").feature("tblp1").set("linemarker", "diamond");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "\u6d41\u4f53\u901f\u5ea6\uff0c\u5b9e\u9a8c", 0);
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").set("expr", "bf.ugy");
    model.result("pg7").feature("lngr2").set("linecolor", "red");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").setIndex("legends", "\u6c14\u4f53\u901f\u5ea6\uff0c\u4eff\u771f", 0);
    model.result("pg7").run();
    model.result("pg7").create("tblp2", "Table");
    model.result("pg7").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp2").set("linewidth", "preference");
    model.result("pg7").feature("tblp2").set("table", "tbl2");
    model.result("pg7").feature("tblp2").set("linestyle", "none");
    model.result("pg7").feature("tblp2").set("linecolor", "red");
    model.result("pg7").feature("tblp2").set("linemarker", "square");
    model.result("pg7").feature("tblp2").set("legend", true);
    model.result("pg7").feature("tblp2").set("legendmethod", "manual");
    model.result("pg7").feature("tblp2").setIndex("legends", "\u6c14\u4f53\u901f\u5ea6\uff0c\u5b9e\u9a8c", 0);
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 0.15);
    model.result("pg7").set("ymin", -0.15);
    model.result("pg7").set("ymax", 0.85);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u63a2\u9488\u4f4d\u7f6e #5");
    model.result("pg8").set("data", "cln2");
    model.result("pg8")
         .set("title", "\u63a2\u9488\u4f4d\u7f6e #5 \u5904\u7684\u6db2\u4f53\u548c\u6c14\u4f53\u5782\u76f4\u901f\u5ea6");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("table", "tbl3");
    model.result("pg8").run();
    model.result("pg8").feature("tblp2").set("table", "tbl4");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u63a2\u9488\u4f4d\u7f6e #7");
    model.result("pg9").set("data", "cln3");
    model.result("pg9").run();
    model.result("pg9").feature("tblp1").set("table", "tbl5");
    model.result("pg9").run();
    model.result("pg9").feature("tblp2").set("table", "tbl6");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u63a2\u9488\u4f4d\u7f6e #9");
    model.result("pg10").set("data", "cln4");
    model.result("pg10")
         .set("title", "\u63a2\u9488\u4f4d\u7f6e #9 \u5904\u7684\u6db2\u4f53\u548c\u6c14\u4f53\u5782\u76f4\u901f\u5ea6");
    model.result("pg10").run();
    model.result("pg10").feature("tblp1").set("table", "tbl7");
    model.result("pg10").run();
    model.result("pg10").feature("tblp2").set("table", "tbl8");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg5").run();

    model.title("\u6c14\u5347\u5f0f\u73af\u6d41\u53cd\u5e94\u5668\u4e2d\u7684\u6d41\u52a8");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u6c14\u5347\u5f0f\u73af\u6d41\u53cd\u5e94\u5668\u7684\u591a\u76f8\u6d41\u5efa\u6a21\u3002\u53cd\u5e94\u5668\u4e2d\u5145\u6ee1\u6c34\uff0c\u6c14\u6ce1\u901a\u8fc7\u4e24\u4e2a\u5165\u53e3\u6ce8\u5165\u5e95\u90e8\u3002\u7531\u4e8e\u6d6e\u529b\uff0c\u6c14\u6ce1\u4e0a\u5347\uff0c\u4ea7\u751f\u6db2\u4f53\u7684\u73af\u6d41\u8fd0\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("airlift_loop_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
