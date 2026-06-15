/*
 * submarine_cable_01_introduction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class submarine_cable_01_introduction {

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

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
