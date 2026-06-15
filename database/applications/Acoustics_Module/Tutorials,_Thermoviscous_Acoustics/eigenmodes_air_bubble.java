/*
 * eigenmodes_air_bubble.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class eigenmodes_air_bubble {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/ta", true);
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);

    model.param().set("R0", "10[\u00b5m]");
    model.param().descr("R0", "\u534a\u5f84");
    model.param().set("gamma", "72.9[mN/m]");
    model.param().descr("gamma", "\u8868\u9762\u5f20\u529b\u7cfb\u6570");
    model.param().set("PAWater", "1[atm]");
    model.param().descr("PAWater", "\u6c34\u4e2d\u7684\u538b\u529b");
    model.param().set("PABubble", "PAWater+gamma*2/R0");
    model.param().descr("PABubble", "\u6c14\u6ce1\u5185\u7684\u538b\u529b");
    model.param().set("m", "0");
    model.param().descr("m", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("rhowater", "998.2[kg/m^3]");
    model.param().descr("rhowater", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("Nmu", "1");
    model.param().descr("Nmu", "\u9ecf\u5ea6\u6bd4\u4f8b\u53c2\u6570");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "8*R0");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "3*R0", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "4*R0", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"eta(T)*Nmu"});

    model.component("comp1").physics("ta").prop("EquationSettings").set("ModeExtension", true);
    model.component("comp1").physics("ta").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("ta").prop("EquationForm").setIndex("form", "Frequency", 0);
    model.component("comp1").physics("ta").prop("EquationSettings").set("delta_ta", "ta.iomega");
    model.component("comp1").physics("ta").feature("tam1").set("minput_pressure", "PAWater");
    model.component("comp1").physics("ta").create("tam2", "ThermoviscousAcousticsModel", 2);
    model.component("comp1").physics("ta").feature("tam2").selection().set(3);
    model.component("comp1").physics("ta").feature("tam2").set("minput_pressure", "PABubble");
    model.component("comp1").physics("ta").selection().set(2, 3, 4);
    model.component("comp1").physics("ta").create("sten1", "SurfaceTension", 1);
    model.component("comp1").physics("ta").feature("sten1").selection().set(11, 12);
    model.component("comp1").physics("ta").feature("sten1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("ta").feature("sten1").set("sigma", "gamma");
    model.component("comp1").physics("acpr").selection().set(1, 5);
    model.component("comp1").physics("acpr").prop("EquationSettings").set("m", "m");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_pressure", "PAWater");
    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "Viscothermal");
    model.component("comp1").physics("acpr").create("swr1", "SphericalWaveRadiation", 1);
    model.component("comp1").physics("acpr").feature("swr1").selection().set(9, 14);

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 1);
    model.component("comp1").multiphysics("atb1").selection().set(10, 13);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(11, 12);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "R0/20");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("param").setIndex("pname", "R0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "{0,1,2}", 0);
    model.study("std1").feature("param").setIndex("pname", "R0", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "R0", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "Nmu", 1);
    model.study("std1").feature("param").setIndex("plistarr", "{0.02,0.5,1,2}", 1);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 4);
    model.study("std1").feature("eig").set("shift", "80[kHz]");
    model.study("std1").feature("eig").set("eigwhich", "lr");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("eigref", "250[kHz]");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("revangle", 380);
    model.result().dataset("rev1").set("modenumber", "m");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (atb1)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").setIndex("looplevel", 3, 2);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "atb1.p_t");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u538b (atb1)");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4, 3, 1});
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "atb1.p_t*i");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6c14\u6ce1\u4f4d\u79fb");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "i*ta.sten1.dn");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("expr", new String[]{"i*ta.sten1.dn*nr", "", ""});
    model.result("pg2").feature("surf1").feature("def1").setIndex("expr", 0, 1);
    model.result("pg2").feature("surf1").feature("def1").setIndex("expr", "i*ta.sten1.dn*nz", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6c14\u6ce1\u4f4d\u79fb\u9635\u5217");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayshape", "square");
    model.result("pg3").set("arrayplane", "xz");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").feature("surf1").set("data", "rev1");
    model.result("pg3").feature("surf1").set("looplevel", new int[]{3, 3, 1});
    model.result("pg3").feature("surf1").set("expr", "i*ta.sten1.dn");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("manualindexing", true);
    model.result("pg3").feature("surf1").set("colorlegend", false);
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("expr", new String[]{"i*ta.sten1.dn*nr", "", ""});
    model.result("pg3").feature("surf1").feature("def1").setIndex("expr", 0, 1);
    model.result("pg3").feature("surf1").feature("def1").setIndex("expr", "i*ta.sten1.dn*nz", 2);
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("looplevel", new int[]{1, 3, 1});
    model.result("pg3").feature("surf2").set("rowindex", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature("surf3").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("looplevel", new int[]{2, 3, 1});
    model.result("pg3").feature("surf3").set("rowindex", 2);
    model.result("pg3").feature().duplicate("surf4", "surf3");
    model.result("pg3").feature("surf4").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").set("looplevel", new int[]{4, 3, 1});
    model.result("pg3").feature("surf4").set("rowindex", 3);
    model.result("pg3").feature().duplicate("surf5", "surf4");
    model.result("pg3").feature("surf5").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf5").set("looplevel", new int[]{1, 3, 2});
    model.result("pg3").feature("surf5").set("rowindex", 1);
    model.result("pg3").feature("surf5").set("colindex", 1);
    model.result("pg3").feature().duplicate("surf6", "surf5");
    model.result("pg3").feature("surf6").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf6").set("looplevel", new int[]{2, 3, 2});
    model.result("pg3").feature("surf6").set("rowindex", 2);
    model.result("pg3").feature().duplicate("surf7", "surf6");
    model.result("pg3").feature("surf7").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf7").set("looplevel", new int[]{3, 3, 2});
    model.result("pg3").feature("surf7").set("rowindex", 3);
    model.result("pg3").feature().duplicate("surf8", "surf7");
    model.result("pg3").feature("surf8").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf8").set("looplevel", new int[]{1, 3, 3});
    model.result("pg3").feature("surf8").set("rowindex", 1);
    model.result("pg3").feature("surf8").set("colindex", 2);
    model.result("pg3").feature().duplicate("surf9", "surf8");
    model.result("pg3").feature("surf9").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf9").set("looplevel", new int[]{2, 3, 3});
    model.result("pg3").feature("surf9").set("rowindex", 2);
    model.result("pg3").feature().duplicate("surf10", "surf9");
    model.result("pg3").feature("surf10").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf10").set("looplevel", new int[]{3, 3, 3});
    model.result("pg3").feature("surf10").set("rowindex", 3);
    model.result("pg3").run();

    model.view("view2").set("showgrid", false);

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "freq", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6c14\u6ce1\u5171\u632f");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", "120e3");
    model.result("pg4").set("xmax", "430e3");
    model.result("pg4").set("ymin", -0.05);
    model.result("pg4").set("ymax", 2.05);
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("xaxisdata", 3);
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "circle");
    model.result("pg4").run();
    model.result("pg4").create("lnsg1", "LineSegments");
    model.result("pg4").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg1").set("linewidth", "preference");
    model.result("pg4").feature("lnsg1")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((2-1)*(2+1)*(2+2)*gamma/((rhowater)*R0))", 0);
    model.result("pg4").feature("lnsg1")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((2-1)*(2+1)*(2+2)*gamma/((rhowater)*R0))", 1);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", 0, 0);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", 2, 1);
    model.result("pg4").feature("lnsg1").set("linecolor", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("lnsg2", "LineSegments");
    model.result("pg4").feature("lnsg2").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg2").set("linewidth", "preference");
    model.result("pg4").feature("lnsg2")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((3-1)*(3+1)*(3+2)*gamma/((rhowater)*R0))", 0);
    model.result("pg4").feature("lnsg2")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((3-1)*(3+1)*(3+2)*gamma/((rhowater)*R0))", 1);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", 0, 0);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", 2, 1);
    model.result("pg4").feature("lnsg2").set("linecolor", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("lnsg3", "LineSegments");
    model.result("pg4").feature("lnsg3").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg3").set("linewidth", "preference");
    model.result("pg4").feature("lnsg3")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((4-1)*(4+1)*(4+2)*gamma/((rhowater)*R0))", 0);
    model.result("pg4").feature("lnsg3")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt((4-1)*(4+1)*(4+2)*gamma/((rhowater)*R0))", 1);
    model.result("pg4").feature("lnsg3").setIndex("yexpr", 0, 0);
    model.result("pg4").feature("lnsg3").setIndex("yexpr", 2, 1);
    model.result("pg4").feature("lnsg3").set("linecolor", "black");
    model.result("pg4").run();
    model.result("pg4").create("lnsg4", "LineSegments");
    model.result("pg4").feature("lnsg4").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg4").set("linewidth", "preference");
    model.result("pg4").feature("lnsg4")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt(3*1.4*PAWater/rhowater)*sqrt(1+2*gamma/(PAWater*R0)*(1-1/(3*1.4)))", 0);
    model.result("pg4").feature("lnsg4")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt(3*1.4*PAWater/rhowater)*sqrt(1+2*gamma/(PAWater*R0)*(1-1/(3*1.4)))", 1);
    model.result("pg4").feature("lnsg4").setIndex("yexpr", 0, 0);
    model.result("pg4").feature("lnsg4").setIndex("yexpr", 2, 1);
    model.result("pg4").feature("lnsg4").set("linecolor", "red");
    model.result("pg4").run();
    model.result("pg4").create("lnsg5", "LineSegments");
    model.result("pg4").feature("lnsg5").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg5").set("linewidth", "preference");
    model.result("pg4").feature("lnsg5")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt(3*1*PAWater/rhowater)*sqrt(1+2*gamma/(PAWater*R0)*(1-1/(3*1)))", 0);
    model.result("pg4").feature("lnsg5")
         .setIndex("xexpr", "1/(2*pi*R0)*sqrt(3*1*PAWater/rhowater)*sqrt(1+2*gamma/(PAWater*R0)*(1-1/(3*1)))", 1);
    model.result("pg4").feature("lnsg5").setIndex("yexpr", 0, 0);
    model.result("pg4").feature("lnsg5").setIndex("yexpr", 2, 1);
    model.result("pg4").feature("lnsg5").set("linecolor", "red");
    model.result("pg4").run();
    model.result("pg3").run();

    model.title("\u6c14\u6ce1\u5728\u8868\u9762\u5f20\u529b\u4f5c\u7528\u4e0b\u7684\u7279\u5f81\u6a21\u6001");

    model
         .description("\u672c\u6559\u7a0b\u6a21\u62df\u6c34\u4e2d\u6c14\u6ce1\u7684\u7279\u5f81\u6a21\u6001\u548c\u7279\u5f81\u9891\u7387\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u5176\u4e2d\u5305\u542b\u8868\u9762\u5f20\u529b\u7684\u5f71\u54cd\uff0c\u4ee5\u53ca\u6a21\u62df\u6c14\u6ce1\u7684\u8109\u52a8\u6a21\u5f0f\u548c\u8868\u9762\u6a21\u5f0f\u3002\u6700\u540e\uff0c\u8fd8\u7814\u7a76\u4e86\u5468\u56f4\u6d41\u4f53\u7684\u9ecf\u5ea6\u5bf9\u7279\u5f81\u9891\u7387\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("eigenmodes_air_bubble.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
