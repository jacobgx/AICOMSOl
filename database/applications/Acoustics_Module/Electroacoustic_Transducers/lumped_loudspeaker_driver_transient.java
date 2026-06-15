/*
 * lumped_loudspeaker_driver_transient.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_loudspeaker_driver_transient {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/actd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "50[Hz]", "\u9a71\u52a8\u9891\u7387");
    model.param().set("T0", "1/f0", "f0 \u7684\u5468\u671f");
    model.param().set("N0", "4", "\u8981\u6c42\u89e3\u7684\u8c10\u6ce2");
    model.param().set("M_MD", "33.4[g]", "\u52a8\u8d28\u91cf\uff08\u97f3\u5708\u548c\u632f\u819c\uff09");
    model.param().set("R_E", "7[ohm]", "\u97f3\u5708\u7535\u963b");
    model.param().set("L_E", "6.89[mH]", "\u97f3\u5708\u7535\u611f\uff08\u6052\u5b9a\uff09");
    model.param().set("V0", "15[V]", "\u9a71\u52a8\u7535\u538b\uff08\u5cf0\u503c\uff09");
    model.param().set("V0rms", "V0/sqrt(2)", "\u9a71\u52a8\u7535\u538b\uff08\u5747\u65b9\u6839\uff09");
    model.param().set("a", "12[cm]", "\u9a71\u52a8\u5668\u7684\u6d3b\u585e\u534a\u5f84\uff08\u7b49\u6548\uff09");
    model.param().set("S_D", "a^2*pi", "\u9a71\u52a8\u5668\u7b49\u6548\u9762\u79ef");
    model.param().set("Rair", "20[cm]", "\u7a7a\u6c14\u57df\u534a\u5f84");
    model.param().set("Rpml", "6[cm]", "PML \u5c42\u539a\u5ea6");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "lumped_loudspeaker_driver_transient_bl.txt");
    model.func("int1").importData();
    model.func("int1").set("funcname", "BL");
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").set("extrap", "linear");
    model.func("int1").setIndex("fununit", "Wb/m", 0);
    model.func("int1").setIndex("argunit", "mm", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "lumped_loudspeaker_driver_transient_c_ms.txt");
    model.func("int2").importData();
    model.func("int2").set("funcname", "C_MS");
    model.func("int2").set("extrap", "linear");
    model.func("int2").setIndex("fununit", "mm/N", 0);
    model.func("int2").setIndex("argunit", "mm", 0);
    model.func().create("int3", "Interpolation");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "lumped_loudspeaker_driver_transient_r_ms.txt");
    model.func("int3").importData();
    model.func("int3").set("funcname", "R_MS");
    model.func("int3").set("interp", "cubicspline");
    model.func("int3").setIndex("fununit", "N*s/m", 0);
    model.func("int3").setIndex("argunit", "m/s", 0);
    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", "0.1*T0");
    model.component("comp1").func("rm1").set("slope", "1/T0");
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);
    model.component("comp1").func("rm1").set("smoothzoneloc", "0.2*T0");
    model.component("comp1").func("rm1").set("smoothzonecutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoff", "0.2*T0");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "1[cm]");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"a", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Rair+Rpml");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "Rpml", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "3[cm]", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-4[cm]", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "a-1[cm]", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "a+1[cm]", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "Rair", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "1.8[cm]", 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "3[cm]", 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-3[cm]", 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-3.1[cm]", 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", "-4[cm]", 1, 2);
    model.component("comp1").geom("geom1").feature("qb1").set("w", new double[]{1, 1.5, 1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 3, 4);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.15, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", -0.1, 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", -0.1, 2, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1, 5);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6321\u677f\uff08\u5185\u58c1\uff09");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(9, 11, 12, 19);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u626c\u58f0\u5668");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(8, 15, 18);

    model.component("comp1").physics("actd").create("ishb1", "InteriorSoundHard", 1);
    model.component("comp1").physics("actd").feature("ishb1").selection().named("sel1");
    model.component("comp1").physics("actd").create("ilsb1", "InteriorLumpedSpeakerBoundary", 1);
    model.component("comp1").physics("actd").feature("ilsb1").selection().named("sel2");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/cir", true);

    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0*sin(2*pi*f0*t)*nojac(rm1(t))");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_E");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L_E");
    model.component("comp1").physics("cir").create("H1", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H1").set("gain", "BL(actd.ilsb1.x_ax)[m/Wb*ohm]");
    model.component("comp1").physics("cir").create("H2", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H2").set("device", "R1");
    model.component("comp1").physics("cir").feature("H2").set("gain", "BL(actd.ilsb1.x_ax)[m/Wb*ohm]");
    model.component("comp1").physics("cir").create("L2", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 5, 1, 0);
    model.component("comp1").physics("cir").feature("L2").set("L", "M_MD[H/kg]");
    model.component("comp1").physics("cir").feature("H1").set("device", "L2");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 5, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 6, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R_MS(actd.ilsb1.v_ax)[ohm/kg*s]");
    model.component("comp1").physics("cir").create("G1", "VoltageCurrentSource", -1);
    model.component("comp1").physics("cir").feature("G1")
         .label("\u7535\u538b\u63a7\u5236\u7535\u6d41\u6e90 1 - \u901a\u7528\u7535\u5bb9\u5668");
    model.component("comp1").physics("cir").feature("G1").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("G1").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("cir").feature("G1").setIndex("Connections", 6, 2, 0);
    model.component("comp1").physics("cir").feature("G1").setIndex("Connections", 7, 3, 0);
    model.component("comp1").physics("cir").feature("G1").set("method", "expression");
    model.component("comp1").physics("cir").feature("G1").set("expr", "d(C_MS(actd.ilsb1.x_ax)*sens.v,t)");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.actd.ilsb1.V_cir");
    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "N0*f0");

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

    model.component("comp1").mesh("mesh1").contribute("physics/cir", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T0/30,10*T0)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 301, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (actd)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 301, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b, 3D (actd)");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u8f74\u4e0a\u538b\u529b");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(6);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u4e0a\u538b\u529b FFT");
    model.result("pg4").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").setIndex("interp", "range(2*T0,T0/100,10*T0)", 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5085\u91cc\u53f6\u7cfb\u6570 (dB SPL)");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(6);
    model.result("pg4").feature("ptgr1").set("xdata", "fourier");
    model.result("pg4").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg4").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg4").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg4").feature("ptgr1").set("freqmax", "(N0+1.5)*f0");
    model.result("pg4").feature("ptgr1").set("indb", true);
    model.result("pg4").feature("ptgr1").set("dbtype", "20log");
    model.result("pg4").feature("ptgr1").set("dbref", "manual");
    model.result("pg4").feature("ptgr1").set("dbmanualref", "20e-6");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").create("gmrk1", "GraphMarker");
    model.result("pg4").feature("ptgr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").feature("gmrk1").set("display", "max");
    model.result("pg4").feature("ptgr1").feature("gmrk1").set("scope", "local");
    model.result("pg4").feature("ptgr1").feature("gmrk1").set("precision", 3);
    model.result("pg4").feature("ptgr1").feature("gmrk1").set("labelsuffix", " dB");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u626c\u58f0\u5668\u4f4d\u7f6e x(t)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"actd.ilsb1.x_ax"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u626c\u58f0\u5668\u8f74\u5411\u4f4d\u7f6e"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg5").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u626c\u58f0\u5668\u901f\u5ea6 v(t)");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"actd.ilsb1.v_ax"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u626c\u58f0\u5668\u8f74\u5411\u901f\u5ea6"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"m/s"});
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("C_MS(x(t))");
    model.result("pg7").setIndex("looplevelinput", "interp", 0);
    model.result("pg7").setIndex("interp", "range(5*T0,T0/60,10*T0)", 0);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "C_MS(actd.ilsb1.x_ax)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "C_MS(x(t))", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("BL(x(t))");
    model.result("pg8").setIndex("looplevelinput", "interp", 0);
    model.result("pg8").setIndex("interp", "range(5*T0,T0/60,10*T0)", 0);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "BL(actd.ilsb1.x_ax)", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "BL(x(t))", 0);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("R_MS(v(t))");
    model.result("pg9").setIndex("looplevelinput", "interp", 0);
    model.result("pg9").setIndex("interp", "range(5*T0,T0/60,10*T0)", 0);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "R_MS(actd.ilsb1.v_ax)", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "R_MS(v(t))", 0);
    model.result("pg9").run();

    model.func("int1").createPlot("pg10");

    model.result("pg10").run();
    model.result("pg10").label("\u63d2\u503c\u6570\u636e\uff1aBL(x)");
    model.result("pg10").run();

    model.func("int2").createPlot("pg11");

    model.result("pg11").run();
    model.result("pg11").label("\u63d2\u503c\u6570\u636e\uff1aC_MS(x)");
    model.result("pg11").run();

    model.func("int3").createPlot("pg12");

    model.result("pg12").run();
    model.result("pg12").label("\u63d2\u503c\u6570\u636e\uff1aR_MS(v)");
    model.result("pg12").run();
    model.result("pg10").run();
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg10").set("xlabel", "x (mm)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "BL(x) (Wb/m)");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "x (mm)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "C_MS(x) (mm/N)");
    model.result("pg12").run();
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "v (m/s)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "R_MS(v) (N\\cdot s/m)");
    model.result("pg4").run();

    model
         .title("\u5177\u6709\u975e\u7ebf\u6027\u5927\u4fe1\u53f7\u53c2\u6570\u7684\u96c6\u603b\u626c\u58f0\u5668\u9a71\u52a8\u5668\u77ac\u6001\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5728\u7b80\u5316\u7684\u626c\u58f0\u5668\u5206\u6790\u4e2d\u5305\u542b\u67d0\u4e9b\u96c6\u603b\u5143\u4ef6\u7684\u975e\u7ebf\u6027\uff08\u5927\u4fe1\u53f7\uff09\u7279\u6027\uff0c\u5176\u4e2d\u4f7f\u7528\u7b49\u6548\u7535\u8def\u5bf9\u673a\u68b0\u548c\u7535\u6c14\u7cfb\u7edf\u8fdb\u884c\u5efa\u6a21\u3002\u8fd9\u91cc\u7684\u5927\u4fe1\u53f7\u67d4\u5ea6 CMS(x) \u548c\u529b\u56e0\u5b50 BL(x) \u662f\u626c\u58f0\u5668\u4f4d\u7f6e\u7684\u975e\u7ebf\u6027\u51fd\u6570\u3002\u6b64\u5916\uff0c\u673a\u68b0\u963b\u5c3c RMS(v) \u662f\u626c\u58f0\u5668\u901f\u5ea6\u7684\u51fd\u6570\u3002\u4e0e\u67d4\u5ea6\u548c BL \u56e0\u5b50\u76f8\u5173\u7684\u975e\u7ebf\u6027\u6548\u5e94\u5728\u4f4e\u9891\u4e0b\u5c24\u4e3a\u91cd\u8981\uff0c\u8fd9\u4e5f\u662f\u96c6\u603b\u5efa\u6a21\u65b9\u6cd5\u7684\u4e3b\u8981\u7528\u6b66\u4e4b\u5730\u3002\n\n\u8be5\u6a21\u578b\u4f7f\u7528\u201c\u5185\u90e8\u96c6\u603b\u626c\u58f0\u5668\u8fb9\u754c\u201d\uff08\u6216\u201c\u96c6\u603b\u626c\u58f0\u5668\u8fb9\u754c\u201d\uff09\u7279\u5f81\u4e0e\u201c\u7535\u8def\u201d\u63a5\u53e3\u4e4b\u95f4\u7684\u5185\u7f6e\u8026\u5408\uff0c\u4ee5\u53ca\u626c\u58f0\u5668\u4f4d\u7f6e x \u548c\u901f\u5ea6 v \u7684\u5185\u7f6e\u53d8\u91cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lumped_loudspeaker_driver_transient.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
