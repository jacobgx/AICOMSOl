/*
 * condenser_microphone.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class condenser_microphone {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");
    model.component("comp1").physics("mbrn").field("displacement").field("um");
    model.component("comp1").physics("mbrn").field("displacement").component(new String[]{"um", "vm", "wm"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cir", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ta", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("geometricNonlinearity", true);
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/es", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/cir", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/ta", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/mbrn", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Rmem", "2[mm]", "\u819c\u534a\u5f84");
    model.param().set("Hm", "18[um]", "\u6c14\u9699\u539a\u5ea6");
    model.param().set("tm", "7[um]", "\u819c\u539a\u5ea6");
    model.param().set("G", "3*Hm", "\u7f1d\u9699\u5bbd\u5ea6");
    model.param().set("Tm0", "3150[N/m]", "\u819c\u9759\u6001\u5f20\u529b");
    model.param().set("rhom", "8300[kg/m^3]", "\u819c\u5bc6\u5ea6");
    model.param().set("rhoms", "rhom*tm", "\u819c\u8868\u9762\u5bc6\u5ea6");
    model.param().set("pin", "1[Pa]", "\u5916\u90e8\u5165\u5c04\u538b\u529b");
    model.param().set("p0", "0[Pa]", "\u91ca\u653e\u538b\u529b");
    model.param().set("Em", "221[GPa]", "\u819c\u5f39\u6027\u6a21\u91cf");
    model.param().set("num", "0.4", "\u819c\u6cca\u677e\u6bd4");
    model.param().set("Vpol", "100[V]", "\u6781\u5316\u7535\u538b");
    model.param().set("Rpreamp", "1[Gohm]", "\u524d\u7f6e\u653e\u5927\u5668\u8f93\u51fa\u963b\u6297");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rmem", "Hm"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"G", "Hm"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Rmem-G", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem-G", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem-G", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.05, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.25, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.25, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.25, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.5, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem-G", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.5, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem-G", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.7, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.7, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem", 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Rmem-G", 8, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 8, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(3, 12);
    model.component("comp1").selection("sel1").label("\u819c");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("U", "wm", "\u819c\u4f4d\u79fb\u8c10\u6ce2");
    model.component("comp1").variable("var1")
         .set("U_av", "intop_be(U)/intop_be(1)", "\u5e73\u5747\u8c10\u6ce2\u819c\u53d8\u5f62");
    model.component("comp1").variable("var1")
         .set("Uth", "uth/ta.iomega", "\u819c\u4f4d\u79fb\u7684\u7406\u8bba\u8fd1\u4f3c");
    model.component("comp1").variable("var1")
         .set("Uth_av", "intop_be(Uth)/intop_be(1)", "\u5e73\u5747\u7406\u8bba\u819c\u53d8\u5f62");
    model.component("comp1").variable("var1")
         .set("uth", "ta.iomega*pin/(Tm0*kmsq)*(1-besselj(0,km*r)/besselj(0,km*Rmem))", "\u819c\u901f\u5ea6\u7684\u7406\u8bba\u8fd1\u4f3c");
    model.component("comp1").variable("var1")
         .set("uth_av", "intop_be(uth)/intop_be(1)", "\u5e73\u5747\u7406\u8bba\u819c\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("kmsq", "-ta.iomega^2*rhoms/Tm0", "\u819c\u6ce2\u6570\u5e73\u65b9");
    model.component("comp1").variable("var1").set("km", "sqrt(kmsq)", "\u819c\u6ce2\u6570");
    model.component("comp1").variable("var1")
         .set("um_av", "intop_be(wm*ta.iomega)/intop_be(1)", "\u5e73\u5747\u819c\u901f\u5ea6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_be");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel1");

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

    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(2, 4, 6, 9);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(3, 12, 13, 14);
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.es.V0_1");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "Rpreamp");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").label("\u7535\u538b\u6e90 1 - \u76f4\u6d41\uff1aVpol");
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "Vpol");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "1e-3[ohm]");
    model.component("comp1").physics("cir").create("V2", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V2")
         .label("\u7535\u538b\u6e90 2 - \u4ea4\u6d41\uff1alinper(1)");
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V2").set("value", "linper(1)");
    model.component("comp1").physics("ta").create("pra1", "PressureAdiabatic", 1);
    model.component("comp1").physics("ta").feature("pra1").selection().set(8);
    model.component("comp1").physics("ta").feature("pra1").set("pbnd", "p0");

    model.component("comp1").multiphysics().create("tsb1", "ThermoacousticStructureBoundary", 1);
    model.component("comp1").multiphysics("tsb1").selection().named("sel1");

    model.component("comp1").physics("mbrn").selection().named("sel1");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "tm");
    model.component("comp1").physics("mbrn").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("lemm1").set("E", "Em");
    model.component("comp1").physics("mbrn").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("lemm1").set("nu", "num");
    model.component("comp1").physics("mbrn").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbrn").feature("lemm1").set("rho", "rhom");
    model.component("comp1").physics("mbrn").feature("lemm1").create("iss1", "InitialStressandStrain", 1);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("iss1")
         .set("N0", new String[]{"Tm0", "0", "0", "Tm0"});
    model.component("comp1").physics("mbrn").create("fix1", "Fixed", 0);
    model.component("comp1").physics("mbrn").feature("fix1").selection().set(12);
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 1);
    model.component("comp1").physics("mbrn").feature("fl1").label("\u9762\u8f7d\u8377 1 - \u5165\u5c04\u538b\u529b");
    model.component("comp1").physics("mbrn").feature("fl1").selection().named("sel1");
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "linper(pin)");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(1, 3);
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("fix1", "FixedBoundary");
    model.component("comp1").common("fix1").selection().set(2, 11, 14);
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(1);

    model.component("comp1").multiphysics().create("emfb1", "ElectromechanicsBoundary", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4, 5, 6, 7, 9, 10, 13, 14);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "2[um]");
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").label("\u7814\u7a76 1 - \u9891\u7387\u54cd\u5e94");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"cir/R2", "cir/V2"});
    model.study("std1").feature("frlin").set("punit", "kHz");
    model.study("std1").feature("frlin").set("plist", "{0.1 range(2.5,2.5,300)}");
    model.study("std1").feature("frlin").set("useadvanceddisable", true);
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"cir/R2", "cir/V2"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev1").setIndex("expr", "es.V0_1", 0);
    model.result().numerical("gev1").setIndex("expr", "es.Q0_1", 1);
    model.result().numerical("gev1").set("evalmethod", "linpoint");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "mm", "mm"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b5m", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"velocity", "\u901f\u5ea6", "m/s", "m/s"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm/s", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u901f (ta)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("expr", "ta.v_inst");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u901f (ta)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("evalmethodactive", "off");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("evalmethodactive", "off");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("evalmethodactive", "off");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").run();
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("showlegendsmaxmin", false);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7075\u654f\u5ea6");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7075\u654f\u5ea6\uff08dB\uff0c\u76f8\u5bf9\u4e8e 1 V/Pa\uff09");
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").create("oct1", "OctaveBand");
    model.result("pg3").feature("oct1").set("quantity", "bandpower");
    model.result("pg3").feature("oct1").set("markerpos", "datapoints");
    model.result("pg3").feature("oct1").set("linewidth", "preference");
    model.result("pg3").feature("oct1").selection().geom("geom1");
    model.result("pg3").feature("oct1").set("expr", "es.V0_1");
    model.result("pg3").feature("oct1").set("amplref", "pin/sqrt(2)");
    model.result("pg3").feature("oct1").set("quantity", "continuous");
    model.result("pg3").feature("oct1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("oct1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg3").run();
    model.result("pg3").feature("oct1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg3").feature("oct1").feature("gmrk1").set("intersectionx", "1000, 10000");
    model.result("pg3").feature("oct1").feature("gmrk1").set("showlines", true);
    model.result("pg3").feature("oct1").feature("gmrk1").set("precision", 3);
    model.result("pg3").feature("oct1").feature("gmrk1").set("includeunit", true);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u819c\u53d8\u5f62");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().named("sel1");
    model.result("pg4").feature("lngr1").set("expr", "wm");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "r");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u9759\u6001\u819c\u53d8\u5f62");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("evalmethod", "linpoint");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e73\u5747\u819c\u901f");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "|um<sub>av</sub>| (mm/s)");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "abs(um_av)", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "mm/s", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u6a21\u578b", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "abs(uth_av)", 1);
    model.result("pg6").feature("glob1").setIndex("unit", "mm/s", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u89e3\u6790\u8fd1\u4f3c", 1);
    model.result("pg6").run();
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5e73\u5747\u819c\u53d8\u5f62");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "|U<sub>av</sub>| (\u00b5m)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "abs(U_av)", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "\u00b5m", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u6a21\u578b", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "abs(Uth_av)", 1);
    model.result("pg7").feature("glob1").setIndex("unit", "\u00b5m", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "\u89e3\u6790\u8fd1\u4f3c", 1);
    model.result("pg7").run();
    model.result("pg7").set("xlog", true);
    model.result("pg7").set("ylog", true);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 290);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u819c\u53d8\u5f62\u548c\u538b\u529b");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "mbrn.disp");
    model.result("pg8").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg8").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg8").feature("surf1").create("def1", "Deform");
    model.result("pg8").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("def1").set("expr", new String[]{"um", "vm", "wm"});
    model.result("pg8").feature("surf1").feature("def1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("trn1").set("move", new String[]{"0", "0", "0.1[mm]"});
    model.result("pg8").feature("surf1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("expr", "ta.p_t");
    model.result("pg8").feature("surf2").set("colortable", "Wave");
    model.result("pg8").feature("surf2").set("colorscalemode", "linearsymmetric");
    model.result("pg8").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/physics/cir", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ta", false);
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tsb1", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/emfb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u7535\u5bb9");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"cir/R2", "cir/V2"});
    model.study("std2").create("frlin", "Frequencylinearized");
    model.study("std2").feature("frlin").set("punit", "kHz");
    model.study("std2").feature("frlin").set("plist", "{0.1 range(2.5,2.5,300)}");
    model.study("std2").feature("frlin").set("useadvanceddisable", true);
    model.study("std2").feature("frlin").set("disabledphysics", new String[]{"mbrn/fl1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u9ea6\u514b\u98ce\u7535\u5bb9");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u7535\u5bb9 (pF)");
    model.result("pg9").set("xlog", true);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("data", "dset3");
    model.result("pg9").feature("glob1").setIndex("expr", "abs(es.Q0_1/es.V0_1)", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "pF", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u9891\u7387", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "withsol('sol4',abs(es.Q0_1/es.V0_1))", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "pF", 1);
    model.result("pg9").feature("glob1").setIndex("descr", "\u7a33\u6001", 1);
    model.result("pg9").run();
    model.result("pg8").run();

    model.title("\u8f74\u5bf9\u79f0\u7535\u5bb9\u5f0f\u9ea6\u514b\u98ce");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u4e00\u4e2a\u7b80\u5355\u7684\u8f74\u5bf9\u79f0\u7535\u5bb9\u5f0f\u9ea6\u514b\u98ce\uff0c\u6db5\u76d6\u4e86\u6240\u6709\u76f8\u5173\u7684\u7269\u7406\u73b0\u8c61\uff0c\u5e76\u786e\u5b9a\u4e86\u5728\u7279\u5b9a\u7b80\u5316\u9ea6\u514b\u98ce\u51e0\u4f55\u5f62\u72b6\u548c\u6750\u6599\u53c2\u6570\u4e0b\u9ea6\u514b\u98ce\u7684\u7075\u654f\u5ea6\u548c\u7535\u5bb9\uff0c\u5176\u4e2d\u6c42\u89e3\u58f0-\u7535-\u673a\u68b0\u7cfb\u7edf\u7684\u5168\u8026\u5408\u591a\u7269\u7406\u573a\u6a21\u578b\u3002\u7531\u4e8e\u9ea6\u514b\u98ce\u91c7\u7528\u4e86\u9884\u6781\u5316\u6280\u672f\uff0c\u4f7f\u5f97\u632f\u819c\u4ea7\u751f\u4e86\u9759\u6001\uff08\u76f4\u6d41\uff09\u53d8\u5f62\u548c\u9884\u5f20\u529b\uff0c\u56e0\u6b64\u4f7f\u7528\u9891\u57df\u6270\u52a8\u6c42\u89e3\u5668\u6c42\u89e3\u76f4\u6d41\u5de5\u4f5c\u70b9\u9644\u8fd1\u7684\u7ebf\u6027\u54cd\u5e94\uff0c\u5e76\u5305\u542b\u6d41\u4f53\u57df\u4e2d\u70ed\u9ecf\u6027\u635f\u8017\u548c\u963b\u5c3c\u7684\u8be6\u7ec6\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("condenser_microphone.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
