/*
 * rectangular_horn_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:43 by COMSOL 6.3.0.290. */
public class rectangular_horn_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "c0/1/h0", "\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("R0", "4*h0", "\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("theta0", "15[deg]", "\u6240\u9700\u7684\u6881\u65cb\u8f6c");
    model.param().set("h0", "0.3[m]", "\u5587\u53ed\u9ad8\u5ea6");
    model.param().set("w0", "h0", "\u5587\u53ed\u5bbd\u5ea6");
    model.param().set("L0", "0.8*h0", "\u5587\u53ed\u957f\u5ea6");
    model.param().set("scaleMax", "0.2", "\u6700\u5927\u4f4d\u79fb");
    model.param().set("Rair", "2*h0", "\u7a7a\u6c14\u534a\u5f84");
    model.param().set("V0", "1[V]", "\u9a71\u52a8\u7535\u538b");
    model.param().set("rSpeaker", "L0/2.2", "\u626c\u58f0\u5668\u534a\u5f84");
    model.param().set("backV", "10[cm^3]", "\u540e\u8154\u4f53");
    model.param().set("meshsz", "c0/f0/6", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").geom("geom1")
         .insertFile("rectangular_horn_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel3");

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

    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("geom1_ballsel1");
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition1", 1, 0);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("acpr").create("lsb1", "LumpedSpeakerBoundary", 2);
    model.component("comp1").physics("acpr").feature("lsb1").selection().named("geom1_cylsel1");
    model.component("comp1").physics("acpr").feature("lsb1").set("SpeakerArea", "UseSymmetries");
    model.component("comp1").physics("acpr").feature("lsb1").set("e_ax", new int[]{0, 0, 1});
    model.component("comp1").physics("acpr").feature("lsb1").set("V_back", "backV");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().named("geom1_ballsel1");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "3[ohm]");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "0.25[mH]");
    model.component("comp1").physics("cir").feature().duplicate("L2", "L1");
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("cir").feature("L2").set("L", "6[g]*1[H/kg]");
    model.component("comp1").physics("cir").create("H1", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H1").set("device", "L2");
    model.component("comp1").physics("cir").feature("H1").set("gain", "5[T*m]/1[T*m/ohm]");
    model.component("comp1").physics("cir").feature().duplicate("H2", "H1");
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H2").set("device", "R1");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 8, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "0.7[kg/s]*1[ohm/kg*s]");
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 8, 0, 0);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 9, 1, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "0.4[mm/N]*1[F*N/m]");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.acpr.lsb1.V_cir");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_pyr1_dom");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_sph1_dom");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_sph1_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_ballsel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("view", "new");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg4").feature("rp1").set("thetadisc", 40);
    model.result("pg4").feature("rp1").set("phidisc", 60);
    model.result("pg4").feature("rp1").set("grid", "fine");
    model.result("pg4").feature("rp1").set("colortable", "Rainbow");
    model.result("pg4").feature("rp1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("data", "dset1");
    model.result("pg5").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg5").feature("rp1").set("thetadisc", 40);
    model.result("pg5").feature("rp1").set("phidisc", 60);
    model.result("pg5").feature("rp1").set("colortable", "Cividis");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u5916\u573a\u538b\u529b (acpr)");
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").label("\u5916\u573a\u58f0\u538b\u7ea7 xy \u5e73\u9762 (acpr)");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").label("\u521d\u59cb\u8bbe\u8ba1");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u76ee\u6807\u51fd\u6570");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "Lp_pext_opt(0,0,R0)", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"Lp_pext_opt(0,0,R0)", "acpr.lsb1.P_front"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"", "\u8f90\u5c04\u529f\u7387\uff08\u524d\uff09"});
    model.result().evaluationGroup("eg1").run();

    model.param().set("P0", "2.25[uW]");
    model.param().descr("P0", "\u521d\u59cb\u8f90\u5c04\u529f\u7387");

    model.component("comp1").common().create("cfunc1", "ControlFunction");
    model.component("comp1").common("cfunc1").set("order", "7");
    model.component("comp1").common("cfunc1").set("fmin", "-scaleMax");
    model.component("comp1").common("cfunc1").set("fmax", "scaleMax");
    model.component("comp1").common("cfunc1").set("fleftType", "Dirichlet");
    model.component("comp1").common("cfunc1").set("frightType", "Dirichlet");
    model.component("comp1").common("cfunc1").set("c_0", "0");
    model.component("comp1").common("cfunc1").set("inUnit", "s");
    model.component("comp1").common("cfunc1").set("inQuantity", "none");
    model.component("comp1").common("cfunc1").set("inUnit", "1");
    model.component("comp1").common().create("pres1", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().named("geom1_pyr1_dom");
    model.component("comp1").common("pres1")
         .set("prescribedDeformation", new String[]{"cfunc1((h0+Zg)/h0)*Xg", "cfunc1((h0+Zg)/h0)*Yg", "0"});

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5f62\u72b6\u4f18\u5316 - \u8f74\u4e0a");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 20);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.acpr.lsb1.P_front"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u8f90\u5c04\u529f\u7387\uff08\u524d\uff09"});
    model.study("std2").feature("sho").setIndex("optobj", "comp1.Lp_pext_opt(0,0,R0)", 0);
    model.study("std2").feature("sho").setIndex("descr", "\u8f74\u4e0a\u76ee\u6807", 0);
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("constraintExpression", new String[]{"comp1.acpr.lsb1.P_front"});
    model.study("std2").feature("sho").setIndex("constraintExpression", "log10(comp1.acpr.lsb1.P_front/P0)", 0);
    model.study("std2").feature("sho").setIndex("constraintLbound", "", 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", "", 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", 0, 0);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg7").feature("surf1").set("colortable", "Wave");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b (acpr) 1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("iso1", "Isosurface");
    model.result("pg9").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg9").feature("iso1").set("number", "10");
    model.result("pg9").feature("iso1").set("colortable", "Wave");
    model.result("pg9").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr) 1");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("edges", "off");
    model.result("pg10").set("view", "new");
    model.result("pg10").create("rp1", "RadiationPattern");
    model.result("pg10").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg10").feature("rp1").set("thetadisc", 40);
    model.result("pg10").feature("rp1").set("phidisc", 60);
    model.result("pg10").feature("rp1").set("grid", "fine");
    model.result("pg10").feature("rp1").set("colortable", "Rainbow");
    model.result("pg10").feature("rp1").set("colorscalemode", "linear");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "none");
    model.result("pg11").create("rp1", "RadiationPattern");
    model.result("pg11").feature("rp1").set("data", "dset2");
    model.result("pg11").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg11").feature("rp1").set("thetadisc", 40);
    model.result("pg11").feature("rp1").set("phidisc", 60);
    model.result("pg11").feature("rp1").set("colortable", "Cividis");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").label("\u5916\u573a\u538b\u529b (acpr) 1");
    model.result().create("pg12", "PolarGroup");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").create("rp1", "RadiationPattern");
    model.result("pg12").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg12").feature("rp1").set("legend", true);
    model.result("pg12").feature("rp1").set("phidisc", 180);
    model.result("pg12").label("\u5916\u573a\u58f0\u538b\u7ea7 xy \u5e73\u9762 (acpr) 1");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg13").create("mesh1", "Mesh");
    model.result("pg13").feature("mesh1").set("meshdomain", "volume");
    model.result("pg13").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg13").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg13").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg13").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg13").feature("mesh1").feature("sel1").selection().set(2);
    model.result("pg13").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg13").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg13").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg7").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").add("plotgroup", "pg13");

    return model;
  }

  public static Model run2(Model model) {
    model.nodeGroup("grp2").label("\u5f62\u72b6\u4f18\u5316 - \u8f74\u4e0a");

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg7");

    model.sol("sol2").feature("o1").set("gcmma", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg7").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").set("data", "dset2");
    model.result().dataset("filt1").set("expr", "1");
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp1").label("\u7ec4\u4ef6 1\uff1a\u8f74\u4e0a\u4f18\u5316");
    model.component("comp2").label("\u7ec4\u4ef6 2\uff1a\u79bb\u8f74\u4f18\u5316");

    model.component("comp2").geom("geom2")
         .insertFile("rectangular_horn_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").feature().remove("boxsel2");
    model.component("comp2").geom("geom2").feature().remove("cylsel1");
    model.component("comp2").geom("geom2").feature().remove("boxsel1");
    model.component("comp2").geom("geom2").feature().remove("wp2");
    model.component("comp2").geom("geom2").feature().remove("pyr1");
    model.component("comp2").geom("geom2").run("del1");
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "mesh");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").feature("imp1").set("simplifymesh", false);
    model.component("comp2").geom("geom2").feature("fin").set("repairtoltype", "relative");
    model.component("comp2").geom("geom2").feature("fin").set("repairtol", "1.0E-3");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics().copy("acpr2", "acpr");
    model.component("comp2").physics().copy("cir2", "cir");
    model.component("comp2").physics("acpr2").feature("efc1").selection().named("geom2_ballsel1");
    model.component("comp2").physics("acpr2").feature("lsb1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_cylsel1");
    model.component("comp2").physics("acpr2").feature("lsb1").set("e_ax", new String[]{"-nx", "-ny", "-nz"});
    model.component("comp2").physics("acpr2").feature("pmb1").selection().named("geom2_ballsel1");
    model.component("comp2").physics("acpr2").feature("sym1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_boxsel3");
    model.component("comp2").physics("cir2").feature("IvsU1").set("V_src", "root.comp2.acpr2.lsb1.V_cir");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").label("Air");
    model.component("comp2").material("mat2").set("family", "air");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat2").materialType("nonSolid");

    model.component("comp2").common().create("fsd1", "FreeShapeDomain");
    model.component("comp2").common("fsd1").selection().all();
    model.component("comp2").common("fsd1").selection().named("geom2_imp1_mpart1_imp1_geom1_pyr1_dom");
    model.component("comp2").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp2").common("fsb1").selection().named("geom2_imp1_mpart1_imp1_geom1_boxsel2");
    model.component("comp2").common("fsb1").set("maximumDisplacement", "0.2");
    model.component("comp2").common("fsb1").set("filterRadiusType", "Medium");
    model.component("comp2").common().create("tsf1", "Transformation");
    model.component("comp2").common("tsf1").selection().geom("geom2", 2);
    model.component("comp2").common("tsf1").selection().named("geom2_imp1_mpart1_imp1_geom1_boxsel1");
    model.component("comp2").common("tsf1").setIndex("move_lock", false, 0);
    model.component("comp2").common("tsf1").setIndex("move_lBound", -0.1, 0);
    model.component("comp2").common("tsf1").setIndex("move_uBound", 0.1, 0);
    model.component("comp2").common("tsf1").setIndex("move_lock", false, 2);
    model.component("comp2").common("tsf1").setIndex("move_lBound", -0.1, 2);
    model.component("comp2").common("tsf1").setIndex("move_uBound", 0.1, 2);
    model.component("comp2").common("tsf1").set("scalingType", "No_scaling");
    model.component("comp2").common("tsf1").set("rotateType", "Axis");
    model.component("comp2").common("tsf1").set("rotateAxis", "Yaxis");
    model.component("comp2").common("tsf1").setIndex("rotate_lBound", "-20[deg]", 0);
    model.component("comp2").common("tsf1").setIndex("rotate_uBound", "20[deg]", 0);
    model.component("comp2").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp2").common("fsr1").selection().named("geom2_imp1_mpart1_imp1_geom1_boxsel3");

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_boxsel1");
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "meshsz");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").selection()
         .named("geom2_imp1_mpart1_imp1_geom1_pyr1_dom");
    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().named("geom2_sph1_dom");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().named("geom2_sph1_dom");
    model.component("comp2").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().named("geom2_ballsel1");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 1);

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("freq").setSolveFor("/physics/cir", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std3").feature("freq").setSolveFor("/physics/cir2", true);
    model.study("std3").feature("freq").set("plist", "f0");
    model.study("std2").feature("sho").setEntry("controlVariableActive", "fsb1.c", false);
    model.study("std2").feature("sho").setEntry("controlVariableActive", "tsf1", false);
    model.study("std3").feature().copy("sho", "std2/sho");
    model.study("std3").feature("sho").setIndex("optobj", "comp2.Lp_pext_opt(R0*sin(theta0),0,R0*cos(theta0))", 0);
    model.study("std3").feature("sho").setIndex("descr", "\u79bb\u8f74\u76ee\u6807", 0);
    model.study("std3").feature("sho").setEntry("controlVariableActive", "cfunc1", false);
    model.study("std3").feature("sho").setEntry("controlVariableActive", "fsb1.c", true);
    model.study("std3").feature("sho").setEntry("controlVariableActive", "tsf1", true);
    model.study("std3").feature("sho").setIndex("constraintExpression", "log10(comp2.acpr2.lsb1.P_front/P0)", 0);
    model.study("std3").feature("sho").set("objtable", "new");
    model.study("std3").label("\u7814\u7a76 3\uff1a\u5f62\u72b6\u4f18\u5316 - \u79bb\u8f74");
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg14").feature("surf1").set("colortable", "Wave");
    model.result("pg14").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").label("\u58f0\u538b (acpr2)");
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").set("data", "dset4");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", new String[]{"acpr2.Lp_t"});
    model.result("pg15").feature("surf1").set("colortable", "Rainbow");
    model.result("pg15").feature("surf1").set("colorscalemode", "linear");
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").label("\u58f0\u538b\u7ea7 (acpr2)");
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").set("data", "dset4");
    model.result("pg16").create("iso1", "Isosurface");
    model.result("pg16").feature("iso1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg16").feature("iso1").set("number", "10");
    model.result("pg16").feature("iso1").set("colortable", "Wave");
    model.result("pg16").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg16").set("showlegendsunit", true);
    model.result("pg16").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr2)");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").set("data", "dset4");
    model.result("pg17").set("edges", "off");
    model.result("pg17").set("view", "new");
    model.result("pg17").create("rp1", "RadiationPattern");
    model.result("pg17").feature("rp1").set("expr", new String[]{"acpr2.efc1.Lp_pext"});
    model.result("pg17").feature("rp1").set("thetadisc", 40);
    model.result("pg17").feature("rp1").set("phidisc", 60);
    model.result("pg17").feature("rp1").set("grid", "fine");
    model.result("pg17").feature("rp1").set("colortable", "Rainbow");
    model.result("pg17").feature("rp1").set("colorscalemode", "linear");
    model.result("pg17").set("showlegendsunit", true);
    model.result("pg17").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr2)");
    model.result().create("pg18", "PlotGroup2D");
    model.result("pg18").set("data", "none");
    model.result("pg18").create("rp1", "RadiationPattern");
    model.result("pg18").feature("rp1").set("data", "dset4");
    model.result("pg18").feature("rp1").set("expr", new String[]{"acpr2.efc1.pext"});
    model.result("pg18").feature("rp1").set("thetadisc", 40);
    model.result("pg18").feature("rp1").set("phidisc", 60);
    model.result("pg18").feature("rp1").set("colortable", "Cividis");
    model.result("pg18").set("showlegendsunit", true);
    model.result("pg18").label("\u5916\u573a\u538b\u529b (acpr2)");
    model.result().create("pg19", "PolarGroup");
    model.result("pg19").set("data", "dset4");
    model.result("pg19").create("rp1", "RadiationPattern");
    model.result("pg19").feature("rp1").set("expr", new String[]{"acpr2.efc1.Lp_pext"});
    model.result("pg19").feature("rp1").set("legend", true);
    model.result("pg19").feature("rp1").set("phidisc", 180);
    model.result("pg19").label("\u5916\u573a\u58f0\u538b\u7ea7 xy \u5e73\u9762 (acpr2)");
    model.result().create("pg20", "PlotGroup3D");
    model.result("pg20").set("data", "dset3");
    model.result("pg20").label("\u53d8\u5f62\u51e0\u4f55 1");
    model.result("pg20").create("mesh1", "Mesh");
    model.result("pg20").feature("mesh1").set("meshdomain", "volume");
    model.result("pg20").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg20").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg20").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg20").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg20").feature("mesh1").feature("sel1").selection().set(2);
    model.result("pg20").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg20").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg20").feature("mesh1").set("colorrangeunitinterval", false);
    model.result().create("pg21", "PlotGroup3D");
    model.result().dataset().duplicate("dset4g1", "dset4");
    model.result().dataset("dset4g1")
         .label("\u7814\u7a76 3\uff1a\u5f62\u72b6\u4f18\u5316 - \u79bb\u8f74/\u89e3 3 (4) - \u51e0\u4f55");
    model.result().dataset("dset4g1").set("frametype", "geometry");
    model.result("pg21").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg21").set("data", "dset4");
    model.result("pg21").set("frametype", "geometry");
    model.result("pg21").set("edgecolor", "gray");
    model.result("pg21").set("titletype", "none");
    model.result("pg21").create("line1", "Line");
    model.result("pg21").feature("line1").set("expr", "1");
    model.result("pg21").feature("line1").set("coloring", "uniform");
    model.result("pg21").feature("line1").set("color", "fromtheme");
    model.result("pg21").create("con1", "Surface");
    model.result("pg21").feature("con1").set("expr", "fsd1.rel_disp");
    model.result("pg21").feature("con1").set("colortabletype", "discrete");
    model.result("pg21").feature("con1").set("bandcount", 20);
    model.result("pg21").feature("con1").set("rangecoloractive", true);
    model.result("pg21").feature("con1").set("rangecolormin", 0);
    model.result("pg21").feature("con1").set("rangecolormax", 1);
    model.result("pg21").feature("con1").set("colortable", "RainbowLight");
    model.result("pg21").feature("con1").set("smooth", "none");
    model.result("pg21").create("arws1", "ArrowSurface");
    model.result("pg21").feature("arws1").set("data", "dset4g1");
    model.result("pg21").feature("arws1").set("expr", new String[]{"fsd1.dXg", "fsd1.dYg", "fsd1.dZg"});
    model.result("pg21").feature("arws1").set("scaleactive", true);
    model.result("pg21").feature("arws1").set("scale", "1");
    model.result("pg21").feature("arws1").set("placement", "uniform");
    model.result("pg21").feature("arws1").set("arrowcount", 200);
    model.result("pg21").feature("arws1").create("sel1", "Selection");
    model.result("pg21").feature("arws1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg21").create("arwp1", "ArrowPoint");
    model.result("pg21").feature("arwp1").label("\u5e73\u79fb (\u53d8\u6362 1)");
    model.result("pg21").feature("arwp1").set("expr", new String[]{"tsf1.moveXg", "tsf1.moveYg", "tsf1.moveZg"});
    model.result("pg21").feature("arwp1").set("arrowbase", "head");
    model.result("pg21").feature("arwp1").set("scaleactive", true);
    model.result("pg21").feature("arwp1").set("scale", "1");
    model.result("pg21").feature("arwp1").create("def1", "Deform");
    model.result("pg21").feature("arwp1").feature("def1")
         .set("expr", new String[]{"-tsf1.scaleXg-tsf1.rotateXg", "-tsf1.scaleYg-tsf1.rotateYg", "-tsf1.scaleZg-tsf1.rotateZg"});
    model.result("pg21").feature("arwp1").feature("def1").set("scaleactive", true);
    model.result("pg21").feature("arwp1").feature("def1").set("scale", "1");
    model.result("pg21").feature("arwp1").create("col1", "Color");
    model.result("pg21").feature("arwp1").feature("col1").set("expr", "tsf1.rel_move");
    model.result("pg21").feature("arwp1").feature("col1").set("rangecoloractive", "on");
    model.result("pg21").feature("arwp1").feature("col1").set("rangecolormin", 0);
    model.result("pg21").feature("arwp1").feature("col1").set("rangecolormax", 1);
    model.result("pg21").feature("arwp1").set("inheritplot", "con1");
    model.result("pg21").create("arwp2", "ArrowPoint");
    model.result("pg21").feature("arwp2").label("\u7f29\u653e (\u53d8\u6362 1)");
    model.result("pg21").feature("arwp2").set("expr", new String[]{"tsf1.scaleXg", "tsf1.scaleYg", "tsf1.scaleZg"});
    model.result("pg21").feature("arwp2").set("arrowbase", "head");
    model.result("pg21").feature("arwp2").set("scaleactive", true);
    model.result("pg21").feature("arwp2").set("scale", "1");
    model.result("pg21").feature("arwp2").set("inheritplot", "arwp1");
    model.result("pg21").feature("arwp2").create("def1", "Deform");
    model.result("pg21").feature("arwp2").feature("def1")
         .set("expr", new String[]{"-tsf1.rotateXg", "-tsf1.rotateYg", "-tsf1.rotateZg"});
    model.result("pg21").feature("arwp2").feature("def1").set("scaleactive", true);
    model.result("pg21").feature("arwp2").feature("def1").set("scale", "1");
    model.result("pg21").feature("arwp2").create("col1", "Color");
    model.result("pg21").feature("arwp2").feature("col1").set("expr", "tsf1.rel_scale");
    model.result("pg21").feature("arwp2").feature("col1").set("rangecoloractive", "on");
    model.result("pg21").feature("arwp2").feature("col1").set("rangecolormin", 0);
    model.result("pg21").feature("arwp2").feature("col1").set("rangecolormax", 1);
    model.result("pg21").create("arwp3", "ArrowPoint");
    model.result("pg21").feature("arwp3").label("\u65cb\u8f6c (\u53d8\u6362 1)");
    model.result("pg21").feature("arwp3")
         .set("expr", new String[]{"tsf1.rotateXg", "tsf1.rotateYg", "tsf1.rotateZg"});
    model.result("pg21").feature("arwp3").set("arrowbase", "head");
    model.result("pg21").feature("arwp3").set("scaleactive", true);
    model.result("pg21").feature("arwp3").set("scale", "1");
    model.result("pg21").feature("arwp3").set("inheritplot", "arwp1");
    model.result("pg21").feature("arwp3").create("col1", "Color");
    model.result("pg21").feature("arwp3").feature("col1").set("expr", "tsf1.rel_rotate");
    model.result("pg21").feature("arwp3").feature("col1").set("rangecoloractive", "on");
    model.result("pg21").feature("arwp3").feature("col1").set("rangecolormin", 0);
    model.result("pg21").feature("arwp3").feature("col1").set("rangecolormax", 1);
    model.result("pg14").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg14");
    model.nodeGroup("grp3").add("plotgroup", "pg15");
    model.nodeGroup("grp3").add("plotgroup", "pg16");
    model.nodeGroup("grp3").add("plotgroup", "pg17");
    model.nodeGroup("grp3").add("plotgroup", "pg18");
    model.nodeGroup("grp3").add("plotgroup", "pg19");
    model.nodeGroup("grp3").add("plotgroup", "pg20");
    model.nodeGroup("grp3").add("plotgroup", "pg21");
    model.nodeGroup("grp3").label("\u5f62\u72b6\u4f18\u5316 - \u79bb\u8f74");

    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std2").feature("freq").setSolveFor("/physics/cir2", false);
    model.study("std2").feature("freq").setSolveFor("/frame/material4", false);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std1").feature("freq").setSolveFor("/physics/cir2", false);
    model.study("std1").feature("freq").setSolveFor("/frame/material4", false);
    model.study("std3").feature("sho").set("plotgroup", "pg21");

    model.sol("sol3").feature("o1").set("gcmma", false);

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg14").run();

    model.study("std3").feature("sho").set("probewindow", "");

    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg1").feature("gev3").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "Lp_pext_opt(R0*cos(theta0),0,R0*sin(theta0))", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "acpr2.lsb1.P_front", 1);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u8f90\u5c04\u529f\u7387\uff08\u524d\u7aef\uff09", 1);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg22", "PolarGroup");
    model.result("pg22").run();
    model.result("pg22").label("\u8f90\u5c04\u6837\u5f0f\u6bd4\u8f83");
    model.result("pg22").set("titletype", "none");
    model.result("pg22").set("zeroangle", "up");
    model.result("pg22").create("rp1", "RadiationPattern");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg22").feature("rp1").set("markerpos", "datapoints");
    model.result("pg22").feature("rp1").set("linewidth", "preference");
    model.result("pg22").feature("rp1").set("phidisc", 500);
    model.result("pg22").feature("rp1").set("anglerestr", "manual");
    model.result("pg22").feature("rp1").set("phimin", -90);
    model.result("pg22").feature("rp1").set("phirange", 180);
    model.result("pg22").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg22").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result("pg22").feature("rp1").set("legend", true);
    model.result("pg22").feature("rp1").set("legendmethod", "manual");
    model.result("pg22").feature("rp1").setIndex("legends", "\u521d\u59cb\u8bbe\u8ba1", 0);
    model.result("pg22").feature().duplicate("rp2", "rp1");
    model.result("pg22").run();
    model.result("pg22").feature("rp2").set("data", "dset2");
    model.result("pg22").feature("rp2").setIndex("legends", "\u8f74\u4e0a\u8bbe\u8ba1", 0);
    model.result("pg22").feature().duplicate("rp3", "rp2");
    model.result("pg22").run();
    model.result("pg22").feature("rp3").set("data", "dset4");
    model.result("pg22").feature("rp3").set("expr", "acpr2.efc1.Lp_pext");
    model.result("pg22").feature("rp3").set("descr", "\u5916\u573a\u58f0\u538b\u7ea7");
    model.result("pg22").feature("rp3").setIndex("legends", "\u79bb\u8f74\u8bbe\u8ba1", 0);
    model.result("pg22").run();
    model.result("pg4").run();
    model.result("pg4").feature("rp1").set("anglerestr", "manual");
    model.result("pg4").feature("rp1").set("thetarange", 90);
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("anglerestr", "manual");
    model.result("pg5").feature("rp1").set("thetarange", 90);
    model.result("pg6").run();
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("phidisc", 500);
    model.result("pg6").feature("rp1").set("anglerestr", "manual");
    model.result("pg6").feature("rp1").set("phimin", -90);
    model.result("pg6").feature("rp1").set("phirange", 180);
    model.result("pg6").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg6").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result("pg10").run();
    model.result("pg10").feature("rp1").set("anglerestr", "manual");
    model.result("pg10").feature("rp1").set("thetarange", 90);
    model.result("pg11").run();
    model.result("pg11").feature("rp1").set("anglerestr", "manual");
    model.result("pg11").feature("rp1").set("thetarange", 90);
    model.result("pg12").run();
    model.result("pg12").set("zeroangle", "up");
    model.result("pg12").run();
    model.result("pg12").feature("rp1").set("phidisc", 500);
    model.result("pg12").feature("rp1").set("anglerestr", "manual");
    model.result("pg12").feature("rp1").set("phimin", -90);
    model.result("pg12").feature("rp1").set("phirange", 180);
    model.result("pg12").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg12").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result("pg17").run();
    model.result("pg17").feature("rp1").set("anglerestr", "manual");
    model.result("pg17").feature("rp1").set("thetarange", 90);
    model.result("pg18").run();
    model.result("pg18").feature("rp1").set("anglerestr", "manual");
    model.result("pg18").feature("rp1").set("thetarange", 90);
    model.result("pg19").run();
    model.result("pg19").set("zeroangle", "up");
    model.result("pg19").run();
    model.result("pg19").feature("rp1").set("phidisc", 500);
    model.result("pg19").feature("rp1").set("anglerestr", "manual");
    model.result("pg19").feature("rp1").set("phimin", -90);
    model.result("pg19").feature("rp1").set("phirange", 180);
    model.result("pg19").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg19").feature("rp1").set("refdir", new int[]{0, 0, 1});
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result().dataset("mir1").set("hasvar", true);
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().dataset().duplicate("mir3", "mir2");
    model.result().dataset("mir3").set("data", "dset4");
    model.result().create("pg23", "PlotGroup3D");
    model.result("pg23").run();
    model.result("pg23").label("\u8bbe\u8ba1\u65b9\u6848\u6bd4\u8f83");
    model.result("pg23").set("data", "mir1");
    model.result("pg23").set("titletype", "none");
    model.result("pg23").set("edges", false);
    model.result("pg23").create("line1", "Line");
    model.result("pg23").feature("line1").set("expr", "1");
    model.result("pg23").feature("line1").set("linetype", "tube");
    model.result("pg23").feature("line1").set("radiusexpr", "2e-3");
    model.result("pg23").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg23").feature("line1").set("coloring", "uniform");
    model.result("pg23").feature("line1").set("color", "black");
    model.result("pg23").feature("line1").create("filt1", "Filter");
    model.result("pg23").run();
    model.result("pg23").feature("line1").feature("filt1")
         .set("expr", "mir1z<eps && abs(mir1x)<L0*1.01 && abs(mir1y)<w0*1.01");
    model.result("pg23").run();
    model.result("pg23").create("slc1", "Slice");
    model.result("pg23").feature("slc1").set("quickplane", "zx");
    model.result("pg23").feature("slc1").set("quickynumber", 1);
    model.result("pg23").feature("slc1").create("filt1", "Filter");
    model.result("pg23").run();
    model.result("pg23").feature("slc1").feature("filt1").set("expr", "0<mir1z");
    model.result("pg23").run();
    model.result("pg23").create("surf1", "Surface");
    model.result("pg23").feature("surf1").set("expr", "1");
    model.result("pg23").feature("surf1").set("coloring", "uniform");
    model.result("pg23").feature("surf1").set("color", "gray");
    model.result("pg23").feature("surf1").create("filt1", "Filter");
    model.result("pg23").run();
    model.result("pg23").feature("surf1").feature("filt1")
         .set("expr", "Yg*sign(mir1side-0.5)*L0/w0<Xg && mir1z < -eps && eps<abs(mir1y)");
    model.result("pg23").run();
    model.result("pg23").create("str1", "Streamline");
    model.result("pg23").feature("str1").set("data", "dset1");
    model.result("pg23").feature("str1").set("expr", new String[]{"acpr.Ix", "acpr.Iy", "acpr.Iz"});
    model.result("pg23").feature("str1").set("descr", "\u5f3a\u5ea6");
    model.result("pg23").feature("str1").set("posmethod", "selection");
    model.result("pg23").feature("str1").selection().named("geom1_cylsel1");
    model.result("pg23").feature("str1").set("linetype", "tube");
    model.result("pg23").feature("str1").set("radiusexpr", "2e-3");
    model.result("pg23").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg23").feature("str1").set("pointtype", "arrow");
    model.result("pg23").feature("str1").set("color", "black");
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("line2", "line1");
    model.result("pg23").run();
    model.result("pg23").feature("line2").set("data", "mir2");
    model.result("pg23").feature("line2").create("trn1", "Transformation");
    model.result("pg23").run();
    model.result("pg23").feature("line2").feature("trn1").set("move", new String[]{"-1.2*Rair", "0.2*Rair", "0"});
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("slc2", "slc1");
    model.result("pg23").run();
    model.result("pg23").feature("slc2").set("data", "mir2");
    model.result("pg23").run();
    model.result("pg23").run();
    model.result("pg23").feature("slc2").set("inheritplot", "slc1");
    model.result("pg23").feature("slc2").feature().copy("trn1", "pg23/line2/trn1");
    model.result("pg23").run();
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("surf2", "surf1");
    model.result("pg23").run();
    model.result("pg23").feature("surf2").set("data", "mir2");
    model.result("pg23").feature("surf2").feature().copy("trn1", "pg23/line2/trn1");
    model.result("pg23").run();
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("str2", "str1");
    model.result("pg23").run();
    model.result("pg23").feature("str2").set("data", "dset2");
    model.result("pg23").feature("str2").feature().copy("trn1", "pg23/line2/trn1");
    model.result("pg23").run();
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("line3", "line2");
    model.result("pg23").run();
    model.result("pg23").feature("line3").set("data", "mir3");
    model.result("pg23").run();
    model.result("pg23").feature("line3").feature("trn1").set("move", new String[]{"-2.4*Rair", "0.4*Rair", "0"});
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("slc3", "slc2");
    model.result("pg23").run();
    model.result("pg23").feature("slc3").set("data", "mir3");
    model.result("pg23").feature("slc3").set("expr", "acpr2.p_t");
    model.result("pg23").feature("slc3").set("descr", "\u603b\u58f0\u538b");
    model.result("pg23").run();
    model.result("pg23").feature("slc3").feature("trn1").set("move", new String[]{"-2.4*Rair", "0.4*Rair", "0"});
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("surf3", "surf2");
    model.result("pg23").run();
    model.result("pg23").feature("surf3").set("data", "mir3");
    model.result("pg23").run();
    model.result("pg23").feature("surf3").feature("trn1").set("move", new String[]{"-2.4*Rair", "0.4*Rair", "0"});
    model.result("pg23").run();
    model.result("pg23").feature().duplicate("str3", "str2");
    model.result("pg23").run();
    model.result("pg23").feature("str3").set("data", "dset4");
    model.result("pg23").feature("str3").set("expr", new String[]{"acpr2.Ix", "acpr2.Iy", "acpr2.Iz"});
    model.result("pg23").feature("str3")
         .set("descr", "\u5f3a\u5ea6 \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg23").feature("str3").selection().named("geom2_imp1_mpart1_imp1_geom1_cylsel1");
    model.result("pg23").run();
    model.result("pg23").feature("str3").feature("trn1").set("move", new String[]{"-2.4*Rair", "0.4*Rair", "0"});
    model.result("pg23").run();
    model.result("pg23").run();

    model.title("\u77e9\u5f62\u626c\u58f0\u5668\u5587\u53ed\u7684\u5f62\u72b6\u4f18\u5316 - \u4e09\u7ef4");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u63a7\u5236\u51fd\u6570\u201d\u7279\u5f81\u5bf9\u77e9\u5f62\u5587\u53ed\u6267\u884c\u5f62\u72b6\u4f18\u5316\uff0c\u4ee5\u63d0\u9ad8\u8f74\u4e0a\u54cd\u5e94\uff1b\u5e76\u901a\u8fc7\u201c\u8fc7\u6ee4\u5668\u201d\u6570\u636e\u96c6\u5c06\u521d\u59cb\u8bbe\u8ba1\u7684\u7ed3\u679c\u5bfc\u51fa\u81f3\u65b0\u7ec4\u4ef6\u3002\u6b64\u5916\uff0c\u8fd8\u4f7f\u7528\u201c\u81ea\u7531\u5f62\u72b6\u8fb9\u754c\u201d\u548c\u201c\u53d8\u6362\u201d\u5f62\u72b6\u4f18\u5316\u7279\u5f81\u6765\u63d0\u9ad8\u79bb\u8f74\u7a7a\u95f4\u54cd\u5e94\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("acpr_pmb1_xdim").clearMesh();
    model.mesh("cfunc1_xdim").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();
    model.mesh("acpr2_pmb1_xdim").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("rectangular_horn_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
