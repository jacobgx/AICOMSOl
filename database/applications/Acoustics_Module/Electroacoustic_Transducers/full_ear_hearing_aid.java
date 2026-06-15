/*
 * full_ear_hearing_aid.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class full_ear_hearing_aid {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a_vent", "0.27[mm]", "\u901a\u6c14\u5b54\u534a\u5f84");
    model.param().set("a_port", "0.715[mm]", "\u626c\u58f0\u5668\u534a\u5f84");
    model.param().set("V0rms", "100[mV]", "\u9a71\u52a8\u5668 RMS \u7535\u538b");
    model.param().set("V0", "sqrt(2)*V0rms", "\u9a71\u52a8\u5668\u7535\u538b");
    model.param().set("h_max", "343[m/s]/fmax/12", "\u6700\u5927\u7f51\u683c\u5355\u5143\u957f\u5ea6");
    model.param().set("fmax", "20000[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("gain", "0", "\u52a9\u542c\u5668\u589e\u76ca");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").insertFile("full_ear_hearing_aid_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("ige1", 1, 2, 3, 153, 154);

    model.component("comp1").geom("geom1").run("ige1");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "full_ear_hearing_aid_impedance.txt");
    model.func("int1").setIndex("argunit", "1", 0);
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("funcnames", "col2", "absZ");
    model.func("int1").setIndex("fununit", "kg/(m^2*s)", 0);
    model.func("int1").setEntry("funcnames", "col3", "argZ");
    model.func("int1").setIndex("fununit", "rad", 1);
    model.func("int1").importData();
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "full_ear_hearing_aid_abcd.dat");
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("columnType", "col5", "value");
    model.func("int2").setEntry("columnType", "col6", "value");
    model.func("int2").setEntry("columnType", "col7", "value");
    model.func("int2").setEntry("columnType", "col8", "value");
    model.func("int2").setEntry("columnType", "col9", "value");
    model.func("int2").setIndex("argunit", "Hz", 0);
    model.func("int2").setEntry("funcnames", "col2", "Ar");
    model.func("int2").setEntry("funcnames", "col3", "Ai");
    model.func("int2").setEntry("funcnames", "col4", "Br");
    model.func("int2").setEntry("funcnames", "col5", "Bi");
    model.func("int2").setEntry("funcnames", "col6", "Cr");
    model.func("int2").setEntry("funcnames", "col7", "Ci");
    model.func("int2").setEntry("funcnames", "col8", "Dr");
    model.func("int2").setEntry("funcnames", "col9", "Di");
    model.func("int2").importData();
    model.func().create("int3", "Interpolation");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "full_ear_hearing_aid_measurements.txt");
    model.func("int3").setEntry("columnType", "col2", "value");
    model.func("int3").setEntry("columnType", "col3", "value");
    model.func("int3").setEntry("columnType", "col5", "value");
    model.func("int3").setEntry("columnType", "col6", "value");
    model.func("int3").setEntry("columnType", "col7", "value");
    model.func("int3").setEntry("columnType", "col8", "value");
    model.func("int3").setEntry("columnType", "col9", "value");
    model.func("int3").setIndex("argunit", "Hz", 0);
    model.func("int3").setEntry("funcnames", "col2", "L1");
    model.func("int3").setIndex("fununit", "dB", 0);
    model.func("int3").setEntry("funcnames", "col3", "L2");
    model.func("int3").setIndex("fununit", "dB", 1);
    model.func("int3").setEntry("funcnames", "col4", "L3");
    model.func("int3").setIndex("fununit", "dB", 2);
    model.func("int3").setEntry("funcnames", "col5", "L4");
    model.func("int3").setIndex("fununit", "dB", 3);
    model.func("int3").setEntry("funcnames", "col6", "L5");
    model.func("int3").setIndex("fununit", "dB", 4);
    model.func("int3").setEntry("funcnames", "col7", "Lav");
    model.func("int3").setIndex("fununit", "dB", 5);
    model.func("int3").setEntry("funcnames", "col8", "Lpsigma");
    model.func("int3").setIndex("fununit", "dB", 6);
    model.func("int3").setEntry("funcnames", "col9", "Lmsigma");
    model.func("int3").setIndex("fununit", "dB", 7);
    model.func("int3").importData();
    model.func().create("int4", "Interpolation");
    model.func("int4").set("source", "file");
    model.func("int4").set("filename", "full_ear_hearing_aid_microphonesensitivity.txt");
    model.func("int4").setIndex("argunit", "Hz", 0);
    model.func("int4").setEntry("columnType", "col2", "value");
    model.func("int4").setEntry("columnType", "col3", "value");
    model.func("int4").setEntry("columnType", "col4", "none");
    model.func("int4").setEntry("funcnames", "col2", "Tr");
    model.func("int4").setIndex("fununit", "V/Pa", 0);
    model.func("int4").setEntry("funcnames", "col3", "Ti");
    model.func("int4").setIndex("fununit", "V/Pa", 1);
    model.func("int4").importData();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(79);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(183, 198);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(7, 13);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(4, 5, 8, 9, 10, 11, 12);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(3, 6);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(145);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(13, 14, 27);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "aveop_ed");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(145);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "aveop_mic");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().set(183, 198);

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

    model.component("comp1").physics("acpr").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("acpr").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("acpr").feature("lport1").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("lport1").set("TwoPortRepresentation", "Electroacoustic");
    model.component("comp1").physics("acpr").feature("lport1").set("T11_ea_in", "Ar(freq)+i*Ai(freq)");
    model.component("comp1").physics("acpr").feature("lport1").set("T12_ea_in", "Br(freq)+i*Bi(freq)");
    model.component("comp1").physics("acpr").feature("lport1").set("T21_ea_in", "Cr(freq)+i*Ci(freq)");
    model.component("comp1").physics("acpr").feature("lport1").set("T22_ea_in", "Dr(freq)+i*Di(freq)");
    model.component("comp1").physics("acpr").feature("lport1")
         .set("V_in", "V0+(Tr(freq)+i*Ti(freq))*aveop_mic(acpr.p_t)*gain");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").selection().named("sel6");
    model.component("comp1").physics("acpr").feature("imp1")
         .set("Zn", "absZ(log10(freq[1/Hz]))*exp(i*argZ(log10(freq[1/Hz])))");
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 2);
    model.component("comp1").physics("acpr").feature("tvb1").selection().named("sel7");
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().named("sel3");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra1").set("a", "a_vent");
    model.component("comp1").physics("acpr").create("nra2", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra2").selection().named("sel4");
    model.component("comp1").physics("acpr").feature("nra2").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra2").set("a", "0.5*0.4[mm]");
    model.component("comp1").physics("acpr").create("nra3", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra3").selection().named("sel5");
    model.component("comp1").physics("acpr").feature("nra3").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra3").set("a", "a_port");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().set(1, 2, 3, 153, 154);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(172);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection()
         .set(46, 47, 124, 125, 126, 127, 128, 129, 130, 131, 133, 134, 138, 139, 140, 141, 142, 143, 144, 149, 150, 151, 152, 155, 156);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(4, 5, 7, 8, 9, 10, 11, 12, 13);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "a_vent/3");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h_max");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "h_max/2");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{10^{range(log10(20),1/20,log10(950))} 10^{range(log10(1000),1/60,log10(20000))}}");
    model.study("std1").feature("freq").set("probesel", "none");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "a_vent", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "a_vent", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "gain", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0, 500", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 113, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 113, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 113, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("oct1", "OctaveBand");
    model.result("pg4").feature("oct1").set("quantity", "bandpower");
    model.result("pg4").feature("oct1").set("markerpos", "datapoints");
    model.result("pg4").feature("oct1").set("linewidth", "preference");
    model.result("pg4").feature("oct1").selection().geom("geom1", 2);
    model.result("pg4").feature("oct1").selection().set(145);
    model.result("pg4").feature("oct1").set("amplref", "acpr.pref_SPL*sqrt(2)");
    model.result("pg4").feature("oct1").set("quantity", "continuous");
    model.result("pg4").feature("oct1").set("legend", true);
    model.result("pg4").feature("oct1").set("legendmethod", "manual");
    model.result("pg4").feature("oct1").setIndex("legends", "COMSOL Model", 0);
    model.result("pg4").feature("oct1").create("gmrk1", "GraphMarker");
    model.result("pg4").feature("oct1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg4").run();
    model.result("pg4").feature("oct1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg4").feature("oct1").feature("gmrk1").set("intersectionx", "700 3000 8000");
    model.result("pg4").feature("oct1").feature("gmrk1").set("showlines", true);
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "L1(freq)", 0);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "Measurements (1-5)", 0);
    model.result("pg4").feature("glob1").set("linestyle", "dashed");
    model.result("pg4").feature("glob1").set("linecolor", "black");
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("legend", false);
    model.result("pg4").feature("glob2").setIndex("expr", "L2(freq)", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "L3(freq)", 1);
    model.result("pg4").feature("glob2").setIndex("expr", "L4(freq)", 2);
    model.result("pg4").feature("glob2").setIndex("expr", "L5(freq)", 3);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("glob3", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob3").setIndex("expr", "Lav(freq)", 0);
    model.result("pg4").feature("glob3").set("linestyle", "solid");
    model.result("pg4").feature("glob3").set("linecolor", "red");
    model.result("pg4").feature("glob3").set("linewidth", 2);
    model.result("pg4").feature("glob3").setIndex("legends", "Average", 0);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("glob4", "glob3");
    model.result("pg4").run();
    model.result("pg4").feature("glob4").setIndex("expr", "Lmsigma(freq)", 0);
    model.result("pg4").feature("glob4").setIndex("expr", "Lpsigma(freq)", 1);
    model.result("pg4").feature("glob4").set("linestyle", "dashed");
    model.result("pg4").feature("glob4").set("linewidth", "preference");
    model.result("pg4").feature("glob4").set("legend", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "first", 1);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("oct1", "OctaveBand");
    model.result("pg5").feature("oct1").set("quantity", "bandpower");
    model.result("pg5").feature("oct1").set("markerpos", "datapoints");
    model.result("pg5").feature("oct1").set("linewidth", "preference");
    model.result("pg5").feature("oct1").selection().geom("geom1", 2);
    model.result("pg5").feature("oct1").selection().set(182);
    model.result("pg5").feature("oct1").set("expr", "abs((Tr(freq)+i*Ti(freq))*acpr.p_t)");
    model.result("pg5").feature("oct1").set("exprtype", "general");
    model.result("pg5").feature("oct1").set("generalref", "aveop_ed(abs(acpr.p_t))");
    model.result("pg5").feature("oct1").set("quantity", "continuous");
    model.result("pg5").feature("oct1").set("legend", true);
    model.result("pg5").feature("oct1").set("legendmethod", "manual");
    model.result("pg5").feature("oct1").setIndex("legends", "Front microphone", 0);
    model.result("pg5").feature().duplicate("oct2", "oct1");
    model.result("pg5").run();
    model.result("pg5").feature("oct2").selection().set(197);
    model.result("pg5").feature("oct2").setIndex("legends", "Back microphone", 0);
    model.result("pg5").set("ylog", true);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(338);
    model.result("pg5").feature("ptgr1").set("expr", "arg(acpr.p_t)");
    model.result("pg5").feature("ptgr1").set("linecolor", "cyclereset");
    model.result("pg5").feature("ptgr1").set("linestyle", "dashed");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "Front Microphone - Phase", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").selection().set(289);
    model.result("pg5").feature("ptgr2").set("linecolor", "cycle");
    model.result("pg5").feature("ptgr2").setIndex("legends", "Back Microphone - Phase", 0);
    model.result("pg5").run();
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg5").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("yseclabelactive", true);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 58, 0);
    model.result("pg2").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().duplicate("pg6", "pg2");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 1);
    model.result("pg6").set("legendpos", "bottom");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayshape", "square");
    model.result("pg6").feature("surf1").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("data", "dset1");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature("surf2").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").setIndex("looplevel", 53, 0);
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").feature("surf3").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").setIndex("looplevel", 21, 0);
    model.result("pg6").feature().duplicate("surf4", "surf3");
    model.result("pg6").feature("surf4").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").setIndex("looplevel", 1, 0);

    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").setIndex("looplevel", 62, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 90, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 105, 0);
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("oct1", "OctaveBand");
    model.result("pg7").feature("oct1").set("quantity", "bandpower");
    model.result("pg7").feature("oct1").set("markerpos", "datapoints");
    model.result("pg7").feature("oct1").set("linewidth", "preference");
    model.result("pg7").feature("oct1").selection().geom("geom1", 2);
    model.result("pg7").feature("oct1").selection().set(145);
    model.result("pg7").feature("oct1").set("amplref", "acpr.pref_SPL*sqrt(2)");
    model.result("pg7").feature("oct1").set("quantity", "continuous");
    model.result("pg7").feature("oct1").set("legend", true);
    model.result("pg7").feature("oct1").set("descractive", true);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 1, 1);
    model.result("pg8").setIndex("looplevel", 51, 0);
    model.result("pg8").set("edges", false);
    model.result("pg8").set("showlegends", false);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"-acpr.Ix", "-acpr.Iy", ""});
    model.result("pg8").feature("str1").setIndex("expr", "-acpr.Iz", 2);
    model.result("pg8").feature("str1").set("selnumber", 30);
    model.result("pg8").feature("str1").selection().set(3);
    model.result("pg8").feature("str1").set("linetype", "tube");
    model.result("pg8").feature("str1").set("back", false);
    model.result("pg8").feature("str1").create("col1", "Color");
    model.result("pg8").run();
    model.result("pg8").feature("str1").feature("col1").set("expr", "acpr.I_mag");
    model.result("pg8").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result("pg8").feature("str1").feature("col1").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("str1").feature("col1").set("rangecoloractive", true);
    model.result("pg8").feature("str1").feature("col1").set("rangecolormax", "1e-5");
    model.result("pg8").run();
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("color", "gray");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().set(5, 6, 7, 8, 9, 14, 145);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("mtrl1").set("color", "white");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("sel1").selection()
         .set(46, 47, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 138, 139, 140, 141, 142, 143, 144, 149, 150, 151, 152, 155, 156);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("mtrl1").set("family", "copperoxidized");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("sel1").selection()
         .set(158, 160, 162, 164, 165, 166, 170, 171, 172, 173, 174, 177, 178, 179, 180, 181, 182, 184, 185, 186, 187, 188, 189, 191, 192, 193, 194, 195, 196, 197, 199, 200, 202, 203, 204, 205, 206, 207, 208, 209, 210, 213, 218, 219, 220);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf4").feature("mtrl1").set("family", "plastic");
    model.result("pg8").feature("surf4").feature("mtrl1").set("color", "black");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").feature("sel1").selection()
         .set(10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 101, 102, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 135, 136, 137, 146, 147, 148, 157, 159, 161, 163, 167, 168, 169, 175, 176, 183, 198, 201, 211, 212, 214, 215, 216, 217, 221, 222);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf5", "surf4");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf5").feature("mtrl1").set("color", "green");
    model.result("pg8").run();
    model.result("pg8").feature("surf5").feature("sel1").selection().set(190);
    model.result("pg8").run();

    model.title("\u5168\u8033\u52a9\u542c\u5668\u54cd\u5e94");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6267\u884c\u52a9\u542c\u5668\u58f0\u53cd\u9988\u54cd\u5e94\u5206\u6790\uff0c\u6db5\u76d6\u8033\u5185\u5f0f\u52a9\u542c\u5668\u548c\u52a9\u542c\u5668\u76d2\u3002\u6240\u6a21\u62df\u7684\u52a9\u542c\u5668\u662f\u6765\u81ea GN Hearing A/S \u516c\u53f8\u7684\u745e\u58f0\u8fbe\u6d69\u542c (ReSound OMNIA\u2122) \u7cfb\u5217\u8bbe\u5907\u3002\u5176\u4e2d\u7ed3\u5408\u6a21\u62df\u8033\u5ed3\u548c\u8033\u9053\u5bf9\u52a9\u542c\u5668\u8fdb\u884c\u6d4b\u8bd5\uff0c\u8ba1\u7b97\u9f13\u819c\u5904\u7684\u58f0\u538b\u7ea7\u5e76\u5c06\u7ed3\u679c\u4e0e\u6d4b\u91cf\u503c\u8fdb\u884c\u6bd4\u8f83\u3002\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u6a21\u62df\u8033\u5916\u58f0\u573a\uff0c\u5e76\u5206\u6790\u52a9\u542c\u5668\u76d2\u4e0a\u4e24\u4e2a\u9ea6\u514b\u98ce\u7684\u58f0\u53cd\u9988\u60c5\u51b5\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df\u201d\u63a5\u53e3\u901a\u8fc7\u201c\u96c6\u603b\u7aef\u53e3\u201d\u6765\u6a21\u62df\u626c\u58f0\u5668\uff0c\u5e76\u5728\u9002\u5f53\u7684\u57df\u4e2d\u91c7\u7528\u5408\u9002\u7684\u8fb9\u754c\u901a\u8fc7\u201c\u72ed\u7a84\u533a\u57df\u58f0\u5b66\u201d\u548c\u201c\u70ed\u9ecf\u6027\u8fb9\u754c\u5c42\u963b\u6297\u201d\u6765\u6a21\u62df\u963b\u5c3c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("full_ear_hearing_aid.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
