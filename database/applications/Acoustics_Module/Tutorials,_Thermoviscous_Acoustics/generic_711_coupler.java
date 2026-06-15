/*
 * generic_711_coupler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class generic_711_coupler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fmax", "20[kHz]", "\u6700\u5927\u9891\u7387");
    model.param().set("Lmax", "345[m/s]/fmax/10", "fmax \u7684\u7f51\u683c\u5927\u5c0f");
    model.param().set("Cmic", "0.62e-13[m^5/N]", "\u9ea6\u514b\u98ce\u58f0\u987a");
    model.param().set("Lmic", "710[kg/m^4]", "\u9ea6\u514b\u98ce\u58f0\u8d28\u91cf");
    model.param().set("Rmic", "119e6[N*s/m^5]", "\u9ea6\u514b\u98ce\u58f0\u963b");
    model.param().set("Tref", "23[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("d0", "10[um]", "\u8f93\u5165\u6e90\u4f4d\u79fb");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "generic_711_coupler_transfer_impedance.txt");
    model.func("int1").importData();
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("funcnames", "col2", "int_trans");
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("funcnames", "col3", "int_trans_upper");
    model.func("int1").setEntry("funcnames", "col4", "int_trans_lower");
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "generic_711_coupler_mic_response.txt");
    model.func("int2").importData();
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("funcnames", "col2", "int_mic");
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("funcnames", "col3", "int_mic_upper");
    model.func("int2").setEntry("funcnames", "col4", "int_mic_lower");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "generic_711_coupler.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Ztrans", "(intop_mic(p)/intop_mic(1))/intop_in(2*acpr.vz)", "\u58f0\u8f6c\u79fb\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("Zin", "intop_in(acpr.Zac)/intop_in(2)", "\u58f0\u8f93\u5165\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("L0", "Labs-Lref", "\u9ea6\u514b\u98ce\u58f0\u538b\u7ea7\uff08\u5f52\u4e00\u5316\uff09");
    model.component("comp1").variable("var1")
         .set("Labs", "20*log10(abs(intop_mic(p)/intop_mic(1[Pa])))[dB]", "\u7edd\u5bf9\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("Lref", "with(15,Labs)", "500 Hz \u7684\u53c2\u8003\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("Ztrans_slns", "(intop_mic(p3)/intop_mic(1))/intop_in(2*slns.vz)", "\u58f0\u4f20\u9012\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("Zin_slns", "intop_in(slns.Zac)/intop_in(2)", "\u58f0\u8f93\u5165\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("L0_slns", "Labs_slns-Lref_slns", "\u9ea6\u514b\u98ce\u58f0\u538b\u7ea7\uff08\u5f52\u4e00\u5316\uff09");
    model.component("comp1").variable("var1")
         .set("Labs_slns", "20*log10(abs(intop_mic(p3)/intop_mic(1[Pa])))[dB]", "\u7edd\u5bf9\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("Lref_slns", "with(15,Labs_slns)", "500 Hz \u7684\u53c2\u8003\u58f0\u538b\u7ea7");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_in");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(19);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_mic");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(17);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_top_slit");
    model.component("comp1").cpl("intop3").selection().set(3, 6);
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intop_bottom_slit");
    model.component("comp1").cpl("intop4").selection().set(5);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(1, 2, 4);
    model.component("comp1").selection("sel1").label("\u538b\u529b\u58f0\u5b66\uff08\u65e0\u635f\u8017\uff09");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(3, 5, 6);
    model.component("comp1").selection("sel2").label("\u70ed\u9ecf\u6027\u58f0\u5b66");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(1, 5, 10, 15, 21, 25, 27);
    model.component("comp1").selection("sel3").label("\u5bf9\u79f0");

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

    model.component("comp1").physics("acpr").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "Tref");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("sel3");
    model.component("comp1").physics("acpr").create("ndisp1", "NormalDisplacement", 2);
    model.component("comp1").physics("acpr").feature("ndisp1").selection().set(19);
    model.component("comp1").physics("acpr").feature("ndisp1").set("ndisp", "d0");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").selection().set(17);
    model.component("comp1").physics("acpr").feature("imp1").set("ImpedanceModel", "RCL");
    model.component("comp1").physics("acpr").feature("imp1").set("Rac", "Rmic");
    model.component("comp1").physics("acpr").feature("imp1").set("Cac", "Cmic");
    model.component("comp1").physics("acpr").feature("imp1").set("Lac", "Lmic");
    model.component("comp1").physics("ta").feature("tam1").set("minput_temperature", "Tref");
    model.component("comp1").physics("ta").selection().named("sel2");
    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("sel3");

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 2);
    model.component("comp1").multiphysics("atb1").selection().all();

    model.component("comp1").physics().create("acpr2", "PressureAcoustics", "geom1");
    model.component("comp1").physics("acpr2").selection().named("sel2");
    model.component("comp1").physics("acpr2").feature("fpam1").set("minput_temperature", "Tref");
    model.component("comp1").physics("acpr2").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr2").feature("sym1").selection().named("sel3");
    model.component("comp1").physics("acpr2").field("pressure").field("p");
    model.component("comp1").physics("acpr2").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr2").feature("nra1").selection().set(5);
    model.component("comp1").physics("acpr2").feature("nra1").set("DuctType", "RectangularDuct");
    model.component("comp1").physics("acpr2").feature("nra1").set("a_rect", "2230[um]");
    model.component("comp1").physics("acpr2").feature("nra1").set("b_rect", "170[um]");
    model.component("comp1").physics("acpr2").create("nra2", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr2").feature("nra2").selection().set(3, 6);
    model.component("comp1").physics("acpr2").feature("nra2").set("DuctType", "Slit");
    model.component("comp1").physics("acpr2").feature("nra2").set("h", "69[um]");
    model.component("comp1").physics().create("slns", "ThermoviscousAcousticsSLNSApproximation", "geom1");
    model.component("comp1").physics("slns").feature("tam1").set("minput_temperature", "Tref");
    model.component("comp1").physics("slns").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("slns").feature("sym1").selection().named("sel3");
    model.component("comp1").physics("slns").create("ndisp1", "NormalDisplacement", 2);
    model.component("comp1").physics("slns").feature("ndisp1").selection().set(19);
    model.component("comp1").physics("slns").feature("ndisp1").set("ndisp", "d0");
    model.component("comp1").physics("slns").create("imp1", "Impedance", 2);
    model.component("comp1").physics("slns").feature("imp1").selection().set(17);
    model.component("comp1").physics("slns").feature("imp1").set("ImpedanceModel", "RCL");
    model.component("comp1").physics("slns").feature("imp1").set("Rac", "Rmic");
    model.component("comp1").physics("slns").feature("imp1").set("Cac", "Cmic");
    model.component("comp1").physics("slns").feature("imp1").set("Lac", "Lmic");
    model.component("comp1").physics("slns").feature("imp1").set("minput_temperature", "Tref");
    model.component("comp1").physics("slns").feature("wall1").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("slns").feature("wall1").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("slns").create("wall2", "Wall", 2);
    model.component("comp1").physics("slns").feature("wall2").selection().set(12, 14, 23, 24, 34, 35, 37, 38, 40);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(14, 24, 35);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(18, 62, 73, 80);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(19, 27);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 25);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(63, 66);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 36);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(79, 86);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(36, 69);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("numelem", 7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").selection().set(37, 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("numelem", 6);
    model.component("comp1").mesh("mesh1").run("map1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "Lmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.2[mm]");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(17, 19);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "1[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std1").feature("freq").setSolveFor("/physics/slns", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/atb1", true);
    model.study("std1").label("\u7814\u7a76 1 - \u70ed\u9ecf\u6027\u6a21\u578b");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq")
         .set("plist", "{100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3, 4e3, 4.5e3, 5e3, 5.6e3, 6.3e3, 7.1e3, 8e3, 9e3, 1e4, 1.12e4, 1.25e4, 1.4e4, 1.6e4, 1.8e4, 2e4, 2.24e4, 2.5e4}");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"acpr2"});
    model.study("std1").feature("freq").setSolveFor("/physics/slns", false);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"acpr2", "slns"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ta", false);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std2").feature("freq").setSolveFor("/physics/slns", false);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/atb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u72ed\u7a84\u533a\u57df\u58f0\u5b66");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq")
         .set("plist", "{100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3, 4e3, 4.5e3, 5e3, 5.6e3, 6.3e3, 7.1e3, 8e3, 9e3, 1e4, 1.12e4, 1.25e4, 1.4e4, 1.6e4, 1.8e4, 2e4, 2.24e4, 2.5e4}");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "dDef");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/ta", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std3").feature("freq").setSolveFor("/physics/slns", false);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/atb1", false);
    model.study("std3").label("\u7814\u7a76 3 - \u538b\u529b\u58f0\u5b66");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").feature("freq")
         .set("plist", "{100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3, 4e3, 4.5e3, 5e3, 5.6e3, 6.3e3, 7.1e3, 8e3, 9e3, 1e4, 1.12e4, 1.25e4, 1.4e4, 1.6e4, 1.8e4, 2e4, 2.24e4, 2.5e4}");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"acpr2/nra1", "acpr2/nra2"});
    model.study("std3").createAutoSequences("sol");

    model.sol("sol3").runFromTo("st1", "v1");
    model.sol("sol3").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("s1").feature("dDef").active(true);
    model.sol("sol3").runAll();

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std4").feature("freq").setSolveFor("/physics/ta", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std4").feature("freq").setSolveFor("/physics/slns", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/atb1", false);
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").feature("freq")
         .set("plist", "{100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3, 4e3, 4.5e3, 5e3, 5.6e3, 6.3e3, 7.1e3, 8e3, 9e3, 1e4, 1.12e4, 1.25e4, 1.4e4, 1.6e4, 1.8e4, 2e4, 2.24e4, 2.5e4}");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "atb1.p_t");
    model.result("pg1").feature("vol1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 44, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 20, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 46, 0);
    model.result("pg2").label("\u6d41\u7ebf - \u58f0\u5f3a");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u58f0\u5f3a (W/m<sup>3</sup>)");
    model.result("pg2").set("paramindicator", "freq =eval(acpr.freq) Hz");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").setIndex("expr", "if(isnan(acpr.p_t),ta.Ix,acpr.Ix)", 0);
    model.result("pg2").feature("str1").setIndex("expr", "if(isnan(acpr.p_t),ta.Iy,acpr.Iy)", 1);
    model.result("pg2").feature("str1").setIndex("expr", "if(isnan(acpr.p_t),ta.Iz,acpr.Iz)", 2);
    model.result("pg2").feature("str1").selection().set(11, 19, 29, 36, 41);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("str1").set("selnumber", 100);
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").set("expr", "if(isnan(acpr.p_t),(ta.I_mag),(acpr.I_mag))");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result("pg2").feature("str1").feature("col1").set("colorscalemode", "logarithmic");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 32, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f20\u9012\u963b\u6297");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Z<sub>trans</sub> (dB rel. 1MPa\u00b7s/m<sup>3</sup>)");
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "20*log10(abs(Ztrans/1e6[Pa*s/m^3]))", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u70ed\u9ecf\u6027\u6a21\u578b", 0);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").setIndex("expr", "int_trans(freq)", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "\u6807\u51c6 (IEC 60318-4)", 0);
    model.result("pg3").feature("glob2").set("linecolor", "red");
    model.result("pg3").feature("glob2").set("data", "dset1");
    model.result("pg3").feature("glob2").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").feature("glob2")
         .setIndex("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41}, 0);
    model.result("pg3").run();
    model.result("pg3").set("xlog", true);
    model.result("pg3").feature().duplicate("glob3", "glob2");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").setIndex("expr", "int_trans_upper(freq)", 0);
    model.result("pg3").feature("glob3").setIndex("descr", "\u5bb9\u5dee\u4e0a\u9650", 0);
    model.result("pg3").feature("glob3").setIndex("expr", "int_trans_lower(freq)", 1);
    model.result("pg3").feature("glob3").setIndex("descr", "\u5bb9\u5dee\u4e0b\u9650", 1);
    model.result("pg3").feature("glob3").set("linestyle", "dotted");
    model.result("pg3").feature("glob3").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob4", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob4").set("data", "dset2");
    model.result("pg3").feature("glob4").setIndex("descr", "\u72ed\u7a84\u533a\u57df\u58f0\u5b66\u6a21\u578b", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob5", "glob4");
    model.result("pg3").run();
    model.result("pg3").feature("glob5").set("data", "dset3");
    model.result("pg3").feature("glob5")
         .setIndex("descr", "\u538b\u529b\u58f0\u5b66\u6a21\u578b\uff08\u65e0\u635f\u8017\uff09", 0);
    model.result("pg3").feature("glob5").set("linecolor", "magenta");
    model.result("pg3").feature("glob5").set("linestyle", "dashed");
    model.result("pg3").feature().duplicate("glob6", "glob5");
    model.result("pg3").run();
    model.result("pg3").feature("glob6").set("data", "dset4");
    model.result("pg3").feature("glob6").setIndex("expr", "20*log10(abs(Ztrans_slns/1e6[Pa*s/m^3]))", 0);
    model.result("pg3").feature("glob6").set("linecolor", "cyan");
    model.result("pg3").feature("glob6").set("linestyle", "solid");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymin", 18);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u9ea6\u514b\u98ce\u54cd\u5e94");
    model.result("pg4").set("ylabel", "\u9ea6\u514b\u98ce\u54cd\u5e94\uff08dB SPL \u76f8\u5bf9\u4e8e 500 Hz\uff09");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "L0", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u6a21\u578b", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "int_mic(freq)", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob3").setIndex("expr", "int_mic_upper(freq)", 0);
    model.result("pg4").feature("glob3").setIndex("expr", "int_mic_lower(freq)", 1);
    model.result("pg4").run();
    model.result("pg4").feature("glob4").setIndex("expr", "L0", 0);
    model.result("pg4").feature("glob4").setIndex("unit", "", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob5").setIndex("expr", "L0", 0);
    model.result("pg4").feature("glob5").setIndex("unit", "", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob6").setIndex("expr", "L0_slns", 0);
    model.result("pg4").feature("glob6").setIndex("unit", "", 0);
    model.result("pg4").run();
    model.result("pg4").set("ymin", -2);
    model.result("pg4").set("ymax", 35);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f93\u5165\u963b\u6297");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Z<sub>in</sub> (dB rel. 1MPa\u00b7s/m<sup>3</sup>)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "20*log10(abs(Zin/1e6[Pa*s/m^3]))", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u70ed\u9ecf\u6027\u6a21\u578b", 0);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("data", "dset2");
    model.result("pg5").feature("glob2").setIndex("descr", "\u72ed\u7a84\u533a\u57df\u58f0\u5b66\u6a21\u578b", 0);
    model.result("pg5").feature().duplicate("glob3", "glob2");
    model.result("pg5").run();
    model.result("pg5").feature("glob3").set("data", "dset4");
    model.result("pg5").feature("glob3").setIndex("expr", "20*log10(abs(Zin_slns/1e6[Pa*s/m^3]))", 0);
    model.result("pg5").feature().duplicate("glob4", "glob3");
    model.result("pg5").run();
    model.result("pg5").feature("glob4").set("data", "dset3");
    model.result("pg5").feature("glob4").setIndex("expr", "20*log10(abs(Zin/1e6[Pa*s/m^3]))", 0);
    model.result("pg5").feature("glob4")
         .setIndex("descr", "\u538b\u529b\u58f0\u5b66\u6a21\u578b\uff08\u65e0\u635f\u8017\uff09", 0);
    model.result("pg5").feature("glob4").set("linecolor", "magenta");
    model.result("pg5").feature("glob4").set("linestyle", "dashed");
    model.result("pg5").set("xlog", true);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f52\u4e00\u5316\u635f\u8017");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "-intop_top_slit(ta.diss_tot)/intop_in(down(acpr.Iz))", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u9876\u90e8\u72ed\u7f1d\u635f\u8017", 0);
    model.result("pg6").feature("glob1")
         .setIndex("expr", "-intop_bottom_slit(ta.diss_tot)/intop_in(down(acpr.Iz))", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5e95\u90e8\u72ed\u7f1d\u635f\u8017", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "intop_mic(down(acpr.Iz))/intop_in(down(acpr.Iz))", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "\u9ea6\u514b\u98ce\u635f\u8017", 2);
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 49, 0);

    model.title("\u901a\u7528 711 \u8026\u5408\u5668 - \u5c01\u95ed\u8033\u9053\u6a21\u62df\u5668");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u901a\u7528\u8033\u9053\u6a21\u62df\u5668\uff08\u6216\u8026\u5408\u5668\uff09\u6a21\u578b\u3002\u73b0\u5b9e\u751f\u6d3b\u4e2d\u7684\u8026\u5408\u5668\u7528\u4e8e\u6a21\u62df\u6807\u51c6\u4eba\u8033\u9053\u7684\u58f0\u5b66\u7279\u6027\uff0c\u5e76\u53ef\u7528\u4e8e\u5404\u79cd\u8bbe\u5907\u7684\u6d4b\u91cf\u3002\u5b83\u4eec\u4e0d\u4ec5\u5728\u52a9\u542c\u5668\u884c\u4e1a\u5f97\u5230\u4e86\u5e7f\u6cdb\u7684\u5e94\u7528\uff0c\u4e5f\u5728\u5404\u79cd\u58f0\u5b66\u4eba\u4f53\u6a21\u578b\u4e2d\u7528\u4f5c\u8033\u9053\u6765\u5f55\u5236\u4e09\u7ef4\u58f0\u97f3\u3002\u672c\u4f8b\u5c06\u6a21\u578b\u7ed3\u679c\u4e0e IEC \u6807\u51c6\u66f2\u7ebf\u548c\u7eaf\u538b\u529b\u58f0\u5b66\u6a21\u578b\u8ba1\u7b97\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("generic_711_coupler.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
