/*
 * absorptive_muffler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class absorptive_muffler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

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
    model.param().set("fmax", "2800[Hz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("p_in", "1[Pa]", "\u5165\u5c04\u538b\u529b\u6ce2\u632f\u5e45");
    model.param().set("rho_ap", "12[kg/m^3]", "\u73bb\u7483\u68c9\u8868\u89c2\u5bc6\u5ea6");
    model.param().set("d_av", "10[um]", "\u7ea4\u7ef4\u5e73\u5747\u76f4\u5f84");
    model.param().set("R_f", "3.18e-9[N*s/m^2]*(rho_ap/1[kg/m^3])^1.53/d_av^2", "\u6d41\u963b\u7387");
    model.param().set("T0", "20[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("p0", "1[atm]", "\u73af\u5883\u538b\u529b");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("absorptive_muffler_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(28);

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
    model.component("comp1").material("mat2").label("\u5438\u58f0\u5185\u886c");

    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_pressure", "p0");
    model.component("comp1").physics("acpr").create("port1", "Port", 2);
    model.component("comp1").physics("acpr").feature("port1").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", "p_in");
    model.component("comp1").physics("acpr").feature("port1").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port1").feature("cpra1").selection().set(1, 4);
    model.component("comp1").physics("acpr").feature().duplicate("port2", "port1");
    model.component("comp1").physics("acpr").feature("port2").set("m_circ", 1);
    model.component("comp1").physics("acpr").feature("port2").set("PortExcitation", "off");
    model.component("comp1").physics("acpr").feature().duplicate("port3", "port2");
    model.component("comp1").physics("acpr").feature("port3").set("AzimuthalDependency", "Cosine");

    model.nodeGroup().create("grp1", "Physics", "acpr");
    model.nodeGroup("grp1").placeAfter("dcont1");
    model.nodeGroup("grp1").add("port1");
    model.nodeGroup("grp1").add("port2");
    model.nodeGroup("grp1").add("port3");
    model.nodeGroup("grp1").label("\u5165\u53e3");

    model.component("comp1").physics("acpr").create("port4", "Port", 2);
    model.component("comp1").physics("acpr").feature("port4").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("port4").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port4").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port4").feature("cpra1").selection().set(37, 40);
    model.component("comp1").physics("acpr").feature().duplicate("port5", "port4");
    model.component("comp1").physics("acpr").feature("port5").set("m_circ", 1);
    model.component("comp1").physics("acpr").feature().duplicate("port6", "port5");
    model.component("comp1").physics("acpr").feature("port6").set("AzimuthalDependency", "Cosine");

    model.nodeGroup().create("grp2", "Physics", "acpr");
    model.nodeGroup("grp2").placeAfter("dcont1");
    model.nodeGroup("grp2").add("port4");
    model.nodeGroup("grp2").add("port5");
    model.nodeGroup("grp2").add("port6");
    model.nodeGroup("grp2").label("\u51fa\u53e3\u7aef\u53e3");

    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 3);
    model.component("comp1").physics("acpr").feature("pom1").selection().set(2);
    model.component("comp1").physics("acpr").feature("pom1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("pom1").set("SolidMaterial", "mat2");

    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Rf", new String[]{"R_f"});

    model.component("comp1").physics("acpr").prop("MeshControl").set("SizeControlParameter", "Frequency");
    model.component("comp1").physics("acpr").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumFrequency", "fmax");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u65e0\u5185\u886c");
    model.study("std1").feature("freq").set("plist", "range(50,25,fmax)");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"acpr/pom1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 111, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 111, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 111, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f20\u8f93\u635f\u8017\uff0c\u8fde\u7eed");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4")
         .set("ylabel", "\u529f\u7387\uff0c\u5165\u5c04\u6ce2 (dB, \u76f8\u5bf9\u4e8e\u51fa\u5c04\u6ce2)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("oct1", "OctaveBand");
    model.result("pg4").feature("oct1").set("quantity", "bandpower");
    model.result("pg4").feature("oct1").set("markerpos", "datapoints");
    model.result("pg4").feature("oct1").set("linewidth", "preference");
    model.result("pg4").feature("oct1").selection().geom("geom1");
    model.result("pg4").feature("oct1").set("expr", "acpr.port1.P_in");
    model.result("pg4").feature("oct1").set("descr", "\u5165\u5c04\u6a21\u5f0f\u7684\u529f\u7387");
    model.result("pg4").feature("oct1")
         .set("expr", "acpr.port1.P_in/(acpr.port4.P_out+acpr.port5.P_out+acpr.port6.P_out)");
    model.result("pg4").feature("oct1").set("exprtype", "transfer");
    model.result("pg4").feature("oct1").set("quantity", "continuous");
    model.result("pg4").feature("oct1").set("legend", true);
    model.result("pg4").feature("oct1").set("legendmethod", "manual");
    model.result("pg4").feature("oct1").setIndex("legends", "\u975e\u7ebf\u6027", 0);
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5438\u58f0\u5185\u886c");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "range(50,25,fmax)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("oct2", "oct1");
    model.result("pg4").run();
    model.result("pg4").feature("oct2").set("data", "dset2");
    model.result("pg4").feature("oct2").setIndex("legends", "\u5438\u58f0\u5185\u886c", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u4f20\u8f93\u635f\u8017\uff0c1/3 \u500d\u9891\u5e26");
    model.result("pg5").run();
    model.result("pg5").feature("oct1").set("quantity", "bandaveragepsd");
    model.result("pg5").feature("oct1").set("bandtype", "octave3");
    model.result("pg5").run();
    model.result("pg5").feature("oct2").set("quantity", "bandaveragepsd");
    model.result("pg5").feature("oct2").set("bandtype", "octave3");
    model.result("pg5").feature("oct2").set("type", "outline");
    model.result("pg5").feature("oct2").set("linewidth", 2);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f3a\u5ea6");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"acpr.Ix", "acpr.Iy", "acpr.Iz"});
    model.result("pg6").feature("str1").set("descr", "\u5f3a\u5ea6");
    model.result("pg6").feature("str1").selection().set(1);
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").set("radiusexpr", "2");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature("col1").set("expr", "acpr.I_mag");
    model.result("pg6").feature("str1").feature("col1").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result("pg6").feature("str1").feature("col1").set("colorscalemode", "linear");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5f52\u4e00\u5316\u529f\u7387\u5e73\u8861");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("legendlayout", "outside");
    model.result("pg7").set("legendposoutside", "bottom");
    model.result("pg7").set("legendrowcount", 4);
    model.result("pg7").set("ylog", true);
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 10);
    model.result("pg7").set("xmax", 2850);
    model.result("pg7").set("ymin", "9e-5");
    model.result("pg7").set("ymax", 1.1);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port1.P_out/acpr.port1.P_in", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u53cd\u5c04\u529f\u7387 - \u5e73\u9762\u6a21\u5f0f", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port3.P_out/acpr.port1.P_in", 1);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u53cd\u5c04\u529f\u7387 - \u4e8c\u6b21\u89d2\u5411\u6a21\u5f0f", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port4.P_out/acpr.port1.P_in", 2);
    model.result("pg7").feature("glob1").setIndex("descr", "\u4f20\u8f93\u529f\u7387 - \u5e73\u9762\u6a21\u5f0f", 2);
    model.result("pg7").feature("glob1").setIndex("expr", "acpr.port6.P_out/acpr.port1.P_in", 3);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u4f20\u8f93\u529f\u7387 - \u4e8c\u6b21\u89d2\u5411\u6a21\u5f0f", 3);
    model.result("pg7").feature("glob1").set("legendsuffix", " - \u5438\u58f0\u5185\u886c");
    model.result("pg7").run();
    model.result("pg1").run();

    model.title("\u5438\u6536\u5f0f\u6d88\u58f0\u5668");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5185\u71c3\u673a\u6d88\u58f0\u5668\u4e2d\u538b\u529b\u6ce2\u7684\u4f20\u64ad\uff0c\u6f14\u793a\u5982\u4f55\u5206\u6790\u6d88\u58f0\u5668\u4e2d\u7684\u8bf1\u5bfc\u963b\u5c3c\u548c\u963b\u6297\u963b\u5c3c\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u7aef\u53e3\u8fb9\u754c\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("absorptive_muffler.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
