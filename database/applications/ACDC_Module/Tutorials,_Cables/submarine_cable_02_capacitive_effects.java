/*
 * submarine_cable_02_capacitive_effects.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_02_capacitive_effects {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").insertFile("submarine_cable_e_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570 2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Acon", "500[mm^2]", "\u4e3b\u5bfc\u4f53\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2")
         .set("Ncon", "Acon/(pi*(Dcon/2)^2)", "\u5bfc\u4f53\u5806\u79ef\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par2")
         .set("Apbs", "pi*(Dins+Tpbs)*Tpbs", "\u94c5\u5305\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2").set("Lsec1", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 1");
    model.param("par2")
         .set("Lsec2", "1-Lsec1-Lsec3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 2");
    model.param("par2").set("Lsec3", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 3");
    model.param("par2").set("Lcab", "10[km]", "\u6d77\u5e95\u7535\u7f06\u603b\u957f");
    model.param("par2")
         .set("Scab", "1e5", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff09");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u76f8");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_c1_dom", "geom1_c2_dom", "geom1_c3_dom"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u91d1\u5c5e");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"uni1", "geom1_uni3_dom", "geom1_c17_dom", "geom1_c19_dom"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7edd\u7f18\u4f53\uff08\u76f8\u7684\u5916\u90e8\uff09");
    model.component("comp1").selection("dif1").set("add", new String[]{"geom1_c21_dom"});
    model.component("comp1").selection("dif1")
         .set("subtract", new String[]{"uni2", "geom1_uni1_dom", "geom1_uni2_dom"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u6c14\u888b");
    model.component("comp1").selection("dif2").set("add", new String[]{"dif1"});
    model.component("comp1").selection("dif2")
         .set("subtract", new String[]{"geom1_dif1_dom", "geom1_dif2_dom", "geom1_c18_dom", "geom1_dif3_dom"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").label("\u56fa\u4f53\u57df");
    model.component("comp1").selection("dif3").set("add", new String[]{"geom1_c22_dom"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"dif2"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u70ed\u63a5\u89e6");
    model.component("comp1").selection("adj1").set("input", new String[]{"dif1"});
    model.component("comp1").selection("adj1").set("interior", true);

    model.component("comp1").view("view1").set("showmaterial", true);

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
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u9971\u548c\u7802\u783e");
    model.component("comp1").material("mat3").selection().set(1, 7);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat3").set("family", "rock");
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").label("\u805a\u4e59\u70ef");
    model.component("comp1").material("mat4").selection().named("geom1_dif1_dom");
    model.component("comp1").material("mat4").set("family", "oil");
    model.component("comp1").material().duplicate("mat5", "mat4");
    model.component("comp1").material("mat5").label("\u805a\u4e19\u70ef");
    model.component("comp1").material("mat5").selection().named("geom1_dif2_dom");
    model.component("comp1").material("mat5").set("family", "plastic");
    model.component("comp1").material("mat5").set("color", "gray");
    model.component("comp1").material().duplicate("mat6", "mat5");
    model.component("comp1").material("mat6").label("\u4ea4\u8054\u805a\u4e59\u70ef (XLPE)");
    model.component("comp1").material("mat6").selection().named("geom1_uni2_dom");
    model.component("comp1").material("mat6").set("color", "white");
    model.component("comp1").material().duplicate("mat7", "mat6");
    model.component("comp1").material("mat7").label("\u534a\u5bfc\u4f53\u5316\u5408\u7269");
    model.component("comp1").material("mat7").selection().named("geom1_uni1_dom");
    model.component("comp1").material("mat7").set("color", "black");
    model.component("comp1").material().duplicate("mat8", "mat7");
    model.component("comp1").material("mat8").label("\u6ca5\u9752\u5316\u5408\u7269");
    model.component("comp1").material("mat8").selection().named("geom1_dif3_dom");
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat9").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat9").label("Silica glass");
    model.component("comp1").material("mat9").set("family", "custom");
    model.component("comp1").material("mat9").set("diffuse", "custom");
    model.component("comp1").material("mat9").set("ambient", "custom");
    model.component("comp1").material("mat9").set("noise", true);
    model.component("comp1").material("mat9").set("fresnel", 0.99);
    model.component("comp1").material("mat9").set("roughness", 0.02);
    model.component("comp1").material("mat9").set("diffusewrap", 0);
    model.component("comp1").material("mat9").set("reflectance", 0);
    model.component("comp1").material("mat9").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat9").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat9").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat9").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat9").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat9").selection().named("geom1_c18_dom");
    model.component("comp1").material().duplicate("mat10", "mat8");
    model.component("comp1").material("mat10").label("\u4e0d\u9508\u94a2");
    model.component("comp1").material("mat10").selection().named("geom1_c17_dom");
    model.component("comp1").material("mat10").set("family", "steel");
    model.component("comp1").material().duplicate("mat11", "mat10");
    model.component("comp1").material("mat11").label("\u94dc");
    model.component("comp1").material("mat11").selection().named("uni1");
    model.component("comp1").material("mat11").propertyGroup()
         .create("linzRes", "linzRes", "Linearized_resistivity");
    model.component("comp1").material("mat11").set("family", "copper");
    model.component("comp1").material().duplicate("mat12", "mat11");
    model.component("comp1").material("mat12").label("\u94c5");
    model.component("comp1").material("mat12").selection().named("geom1_uni3_dom");
    model.component("comp1").material("mat12").set("family", "lead");
    model.component("comp1").material().duplicate("mat13", "mat12");
    model.component("comp1").material("mat13").label("\u9540\u950c\u94a2");
    model.component("comp1").material("mat13").selection().named("geom1_c19_dom");
    model.component("comp1").material("mat13").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", new String[]{"x"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas")
         .set("ratioofspecificheat", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", new String[]{"0.02897[kg/mol]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", new String[]{"muB(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"eta(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", new String[]{"1.4"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"cs(T)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel")
         .set("BA", new String[]{"(def.gamma+1)/2"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", new String[]{"R_const/Mn"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", new String[]{"Cp(T)"});
    model.component("comp1").material("mat1").propertyGroup("idealGas")
         .set("ratioofspecificheat", new String[]{"1.4"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", new String[]{"0.02897"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"x"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", new String[]{"muB(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"eta(T)"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("ratioofspecificheat", new String[]{"gamma_w(T)"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"cs(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"28"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2020[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"2512[J/(kg*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-18[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"2.25"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.46[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"935[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"2302[J/(kg*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-18[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("relpermittivity", new String[]{"2.36"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.25[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"946[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"1920[J/(kg*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-18[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.46[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"930[kg/m^3]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("heatcapacity", new String[]{"2302[J/(kg*K)]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"2[S/m]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("relpermittivity", new String[]{"2.25"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"10[W/(m*K)]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("density", new String[]{"1055[kg/m^3]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("heatcapacity", new String[]{"2405[J/(kg*K)]"});
    model.component("comp1").material("mat8").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.2e-9[S/m]"});
    model.component("comp1").material("mat8").propertyGroup("def").set("relpermittivity", new String[]{"3.16"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.17[W/(m*K)]"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", new String[]{"1062[kg/m^3]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("heatcapacity", new String[]{"1885[J/(kg*K)]"});
    model.component("comp1").material("mat9").propertyGroup("def").set("relpermeability", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("electricconductivity", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("relpermittivity", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("thermalconductivity", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("Enu").set("E", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("Enu").set("nu", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").set("n", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").set("ki", new String[]{"x"});
    model.component("comp1").material("mat9").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("heatcapacity", new String[]{"703[J/(kg*K)]"});
    model.component("comp1").material("mat9").propertyGroup("def").set("relpermittivity", new String[]{"3.75"});
    model.component("comp1").material("mat9").propertyGroup("def").set("density", new String[]{"2203[kg/m^3]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]"});
    model.component("comp1").material("mat9").propertyGroup("Enu").set("E", new String[]{"73.1e9[Pa]"});
    model.component("comp1").material("mat9").propertyGroup("Enu").set("nu", new String[]{"0.17"});
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").set("n", new String[]{"1.45"});
    model.component("comp1").material("mat9").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.component("comp1").material("mat10").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.46e6[S/m]"});
    model.component("comp1").material("mat10").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"17.5[W/(m*K)]"});
    model.component("comp1").material("mat10").propertyGroup("def").set("density", new String[]{"7920[kg/m^3]"});
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("heatcapacity", new String[]{"475[J/(kg*K)]"});
    model.component("comp1").material("mat11").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat11").propertyGroup("def").set("density", new String[]{"8940[kg/m^3]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("heatcapacity", new String[]{"385[J/(kg*K)]"});
    model.component("comp1").material("mat12").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"35.3[W/(m*K)]"});
    model.component("comp1").material("mat12").propertyGroup("def").set("density", new String[]{"11340[kg/m^3]"});
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("heatcapacity", new String[]{"127[J/(kg*K)]"});
    model.component("comp1").material("mat13").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("thermalconductivity", new String[]{"58[W/(m*K)]"});
    model.component("comp1").material("mat13").propertyGroup("def").set("density", new String[]{"7850[kg/m^3]"});
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("heatcapacity", new String[]{"475[J/(kg*K)]"});

    model.component("comp1").mesh("mesh1").run();

    model.title("\u6d77\u5e95\u7535\u7f06 1 - \u5165\u95e8");

    model
         .description("\u672c\u6559\u7a0b\u751f\u6210\u7684\u6a21\u578b\u53ef\u4ee5\u4f5c\u4e3a\u672c\u7cfb\u5217\u540e\u7eed\u6559\u7a0b\uff08\u201c\u7535\u5bb9\u6548\u5e94\u201d\u3001\u201c\u7535\u611f\u6548\u5e94\u201d\u548c\u201c\u70ed\u6548\u5e94\u201d\u6559\u7a0b\uff09\u7684\u6a21\u677f\uff0c\u5176\u4e2d\u5305\u542b\u6807\u51c6\u4e09\u82af\u94c5\u5305 XLPE HVAC \u6d77\u5e95\u7535\u7f06\u7684\u8be6\u7ec6\u4e8c\u7ef4\u51e0\u4f55\u5f62\u72b6\u548c\u7f51\u683c\u3002\u6b64\u5916\uff0c\u8fd8\u63d0\u4f9b\u4e00\u4e9b\u53c2\u6570\u3001\u9009\u62e9\u548c\u6750\u6599\u3002\n\n\u6709\u7ecf\u9a8c\u7684 COMSOL \u7528\u6237\u5982\u679c\u5bf9\u8fd9\u4e9b\u4e3b\u9898\uff08\u51e0\u4f55\u3001\u7f51\u683c\u548c\u9009\u62e9\u7b49\uff09\u4e0d\u611f\u5174\u8da3\uff0c\u53ef\u4ee5\u9009\u62e9\u8df3\u8fc7\u672c\u7cfb\u5217\u7684\u8fd9\u4e00\u90e8\u5206\uff0c\u7ee7\u7eed\u5b66\u4e60\u4ee5\u4e0b\u6559\u7a0b\u4e4b\u4e00\u3002\u4f46\u5982\u679c\u60a8\u521a\u63a5\u89e6 COMSOL Multiphysics \u8f6f\u4ef6\uff0c\u5efa\u8bae\u60a8\u82b1\u4e00\u4e9b\u65f6\u95f4\u6765\u4e86\u89e3\u8fd9\u4e9b\u5185\u5bb9\uff0c\u53ef\u4ee5\u5e2e\u52a9\u60a8\u719f\u6089\u57fa\u7840\u77e5\u8bc6\u3002");

    model.label("submarine_cable_01_introduction.mph");

    model.param().create("par3");
    model.param("par3").label("\u7535\u78c1\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("f0", "50[Hz]", "\u5de5\u4f5c\u9891\u7387");
    model.param("par3").set("w0", "(2*pi*f0[1/Hz])[rad/s]", "\u89d2\u9891\u7387");
    model.param("par3").set("V0", "220[kV]/sqrt(3)", "\u76f8\u5bf9\u5730\u7535\u538b\uff08\u5e45\u503c\uff09");
    model.param("par3").set("I0", "655[A]*sqrt(2)", "\u989d\u5b9a\u7535\u6d41\uff08\u5e45\u503c\uff09");
    model.param("par3").set("Scup", "5.96e7[S/m]", "\u94dc\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Spbs", "4.55e6[S/m]", "\u94c5\u5305\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Sarm", "4.03e6[S/m]", "\u94e0\u88c5\u7ebf\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Mcup", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94dc");
    model.param("par3").set("Mpbs", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94c5\u5305");
    model.param("par3").set("Marm", "100-50*j", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94e0\u88c5\u7ebf");
    model.param("par3")
         .set("Dscup", "min(1/real(sqrt(j*w0*mu0_const*Mcup*Scup)),Dcon/3)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94dc\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dspbs", "min(1/real(sqrt(j*w0*mu0_const*Mpbs*Spbs)),12*Tpbs)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94c5\u5305\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dsarm", "min(1/real(sqrt(j*w0*mu0_const*Marm*Sarm)),Tarm/2)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94e0\u88c5\u7ebf\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rcon", "1/Acon/Scup", "\u6bcf\u76f8\u4e3b\u5bfc\u4f53\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rpbs", "1/Apbs/Spbs", "\u6bcf\u76f8\u94c5\u5305\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Exlpe", "2.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570 XLPE\uff08\u53d6\u81ea IEC 60287\uff09");
    model.param("par3")
         .set("Cpha", "2*pi*epsilon0_const*Exlpe/log((Dins/2-Tscc)/(Dcon/2+Tscc))", "\u6bcf\u76f8\u7535\u5bb9\uff08\u89e3\u6790\uff09");
    model.param("par3").set("Icpha", "w0*Cpha*V0", "\u6bcf\u76f8\u5145\u7535\u7535\u6d41\uff08\u89e3\u6790\uff09");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics("ec").selection().named("geom1_c22_dom");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ec", true);
    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").material("mat6").propertyGroup("def").set("relpermittivity", new String[]{"Exlpe"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("electricconductivity", new String[]{"Scup"});
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("electricconductivity", new String[]{"Spbs"});
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("electricconductivity", new String[]{"Sarm"});

    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(12, 13, 338, 348);
    model.component("comp1").physics("ec").create("cucn2", "CurrentConservation", 2);
    model.component("comp1").physics("ec").feature("cucn2").selection().named("uni2");
    model.component("comp1").physics("ec").create("term1", "DomainTerminal", 2);
    model.component("comp1").physics("ec").feature("term1").label("\u76f8 1");
    model.component("comp1").physics("ec").feature("term1").selection().named("geom1_c1_dom");
    model.component("comp1").physics("ec").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").feature("term1").set("V0", "V0");
    model.component("comp1").physics("ec").create("term2", "DomainTerminal", 2);
    model.component("comp1").physics("ec").feature("term2").label("\u76f8 2");
    model.component("comp1").physics("ec").feature("term2").selection().named("geom1_c2_dom");
    model.component("comp1").physics("ec").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").feature("term2").set("V0", "V0*exp(-120[deg]*j)");
    model.component("comp1").physics("ec").create("term3", "DomainTerminal", 2);
    model.component("comp1").physics("ec").feature("term3").label("\u76f8 3");
    model.component("comp1").physics("ec").feature("term3").selection().named("geom1_c3_dom");
    model.component("comp1").physics("ec").feature("term3").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").feature("term3").set("V0", "V0*exp(+120[deg]*j)");
    model.component("comp1").physics("ec").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn2")
         .set("sigma", new String[]{"5[S/m]", "0", "0", "0", "5[S/m]", "0", "0", "0", "5[S/m]"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(6, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(6, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("resolution", "fine");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("geom1_c21_dom");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\u7535\u6d41\u5bc6\u5ea6\u6a21 (ec)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "sqrt(abs(ec.Jdx)^2+abs(ec.Jdy)^2)");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u4f4d\u79fb\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("expr", "sqrt(abs(ec.Jdx)^2+abs(ec.Jdy)^2)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\u6a21 (ec)");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "sqrt(abs(ec.Jix)^2+abs(ec.Jiy)^2)");
    model.result("pg4").feature("surf1").set("descr", "\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").set("expr", "sqrt(abs(ec.Jix)^2+abs(ec.Jiy)^2)");
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").run();

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.result("pg1").run();
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
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("maxframes", 6);
    model.result().export("anim1").run();

    model.param("par3").set("Vdcon", "I0*Lcab*53[mohm/km]");
    model.param("par3").descr("Vdcon", "\u76f8\u4e0a\u7684\u538b\u964d\uff08\u8fd1\u4f3c\u503c\uff09");
    model.param("par3").set("Vrpbs", "((Icpha*Lcab)/2*Rpbs*Lcab)+473[V]");
    model.param("par3").descr("Vrpbs", "\u5c4f\u853d\u5c42\u4e0a\u7684\u538b\u5347\uff08\u8fd1\u4f3c\u503c\uff09");
    model.param("par3").set("V1", "V0-Vdcon");
    model.param("par3").descr("V1", "\u76f8\u5bf9\u5730\u7535\u538b\uff0810 km \u540e\uff09");
    model.param("par3").set("V2", "0+Vrpbs");
    model.param("par3").descr("V2", "\u5c4f\u853d\u5c42\u5bf9\u5730\u7535\u538b\uff0810 km \u540e\uff09");

    model.component("comp1").physics("ec").feature("term1").set("V0", "V1");
    model.component("comp1").physics("ec").feature().duplicate("term4", "term1");
    model.component("comp1").physics("ec").feature("term4").label("\u5c4f\u853d\u5c42 1");
    model.component("comp1").physics("ec").feature("term4").selection().set(55);
    model.component("comp1").physics("ec").feature("term4").set("V0", "V2");
    model.component("comp1").physics("ec").feature("term2").set("V0", "V1*exp(-120[deg]*j)");
    model.component("comp1").physics("ec").feature().duplicate("term5", "term2");
    model.component("comp1").physics("ec").feature("term5").label("\u5c4f\u853d\u5c42 2");
    model.component("comp1").physics("ec").feature("term5").selection().set(101);
    model.component("comp1").physics("ec").feature("term5").set("V0", "V2*exp(-120[deg]*j)");
    model.component("comp1").physics("ec").feature("term3").set("V0", "V1*exp(+120[deg]*j)");
    model.component("comp1").physics("ec").feature().duplicate("term6", "term3");
    model.component("comp1").physics("ec").feature("term6").label("\u5c4f\u853d\u5c42 3");
    model.component("comp1").physics("ec").feature("term6").selection().set(54);
    model.component("comp1").physics("ec").feature("term6").set("V0", "V2*exp(+120[deg]*j)");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset("dset1").selection().named("dif1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u76f8\u7684\u4ea4\u6d41\u7535\u5bb9");
    model.result().numerical("gev1").setIndex("expr", "(imag(ec.Y11)/ec.omega)/1[m]", 0);
    model.result().numerical("gev1").setIndex("unit", "uF/km", 0);
    model.result().numerical("gev1").setIndex("descr", "\u76f8 1 \u7535\u5bb9", 0);
    model.result().numerical("gev1").setIndex("expr", "(imag(ec.Y22)/ec.omega)/1[m]", 1);
    model.result().numerical("gev1").setIndex("unit", "uF/km", 1);
    model.result().numerical("gev1").setIndex("descr", "\u76f8 2 \u7535\u5bb9", 1);
    model.result().numerical("gev1").setIndex("expr", "(imag(ec.Y33)/ec.omega)/1[m]", 2);
    model.result().numerical("gev1").setIndex("unit", "uF/km", 2);
    model.result().numerical("gev1").setIndex("descr", "\u76f8 3 \u7535\u5bb9", 2);
    model.result().numerical("gev1").setIndex("expr", "Cpha", 3);
    model.result().numerical("gev1").setIndex("unit", "uF/km", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u76f8\u7684\u4ea4\u6d41\u7535\u5bb9");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u76f8\u7684\u5145\u7535\u7535\u6d41");
    model.result().numerical("gev2").setIndex("expr", "abs(ec.I0_1)/1[m]", 0);
    model.result().numerical("gev2").setIndex("unit", "A/km", 0);
    model.result().numerical("gev2").setIndex("descr", "\u76f8 1 \u5145\u7535\u7535\u6d41", 0);
    model.result().numerical("gev2").setIndex("expr", "abs(ec.I0_2)/1[m]", 1);
    model.result().numerical("gev2").setIndex("unit", "A/km", 1);
    model.result().numerical("gev2").setIndex("descr", "\u76f8 2 \u5145\u7535\u7535\u6d41", 1);
    model.result().numerical("gev2").setIndex("expr", "abs(ec.I0_3)/1[m]", 2);
    model.result().numerical("gev2").setIndex("unit", "A/km", 2);
    model.result().numerical("gev2").setIndex("descr", "\u76f8 3 \u5145\u7535\u7535\u6d41", 2);
    model.result().numerical("gev2").setIndex("expr", "Icpha", 3);
    model.result().numerical("gev2").setIndex("unit", "A/km", 3);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u76f8\u7684\u5145\u7535\u7535\u6d41");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().dataset("dset1").selection().named("geom1_c21_dom");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 60);
    model.result().export("anim1").set("repeat", "forever");
    model.result("pg1").run();
    model.result("pg1").run();

    model.view("view3").set("showgrid", true);
    model.view("view3").set("showaxisorientation", true);

    model.result("pg1").run();

    model.title("\u6d77\u5e95\u7535\u7f06 2 - \u7535\u5bb9\u6548\u5e94");

    model
         .description("\u672c\u6559\u7a0b\u8ba8\u8bba\u6807\u51c6\u4e09\u82af\u94c5\u5305 XLPE HVAC \u6d77\u5e95\u7535\u7f06\u7684\u4e8c\u7ef4\u6709\u9650\u5143\u6a21\u578b\uff08\u5176\u4e3b\u5bfc\u7ebf\u7684\u6a2a\u622a\u9762\u79ef\u4e3a 500\u00a0mm\u00b2\uff0c\u76f8\u95f4\u5de5\u4f5c\u7535\u538b\u4e3a 220\u00a0kV\uff09\uff0c\u5bf9\u7535\u5bb9\u6548\u5e94\u8fdb\u884c\u5206\u6790\uff0c\u8bc1\u5b9e\u4e86\u89e3\u6790\u65b9\u6cd5\u5bf9\u4e8e\u7535\u5bb9\u548c\u5145\u7535\u7535\u6d41\u8db3\u591f\u51c6\u786e\u8fd9\u4e00\u5047\u8bbe\uff08\u5305\u542b\u9a8c\u8bc1\uff09\u3002\n\n\u6b64\u5916\uff0c\u8fd8\u8ba8\u8bba\u4e86\u6750\u6599\u5c5e\u6027\u3001\u7535\u7f06\u957f\u5ea6\u53ca\u4e92\u8054\u7c7b\u578b\u7684\u5f71\u54cd\u3002\u8be5\u6a21\u578b\u8bc1\u660e\u4e86\u672c\u7cfb\u5217\u540e\u7eed\u6559\u7a0b\uff08\u5c24\u5176\u662f\u201c\u4e92\u8054\u7535\u5bb9\u201d\u548c\u201c\u7535\u611f\u6548\u5e94\u201d\u6559\u7a0b\u7684\u7b2c 3 \u7ae0\u548c\u7b2c 4 \u7ae0\uff09\u4e2d\u9009\u62e9\u7684\u65b9\u6cd5\u662f\u6b63\u786e\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("submarine_cable_02_capacitive_effects.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
