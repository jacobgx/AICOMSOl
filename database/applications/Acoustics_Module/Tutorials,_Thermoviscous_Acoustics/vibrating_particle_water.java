/*
 * vibrating_particle_water.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class vibrating_particle_water {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a_s", "1[mm]", "\u6e90\u534a\u5f84");
    model.param().set("a_ta", "90[mm]", "\u70ed\u9ecf\u6027\u58f0\u5b66\u57df\u534a\u5f84");
    model.param().set("a_tot", "100[mm]", "\u603b\u534a\u5f84");
    model.param().set("f0", "50[kHz]", "\u7814\u7a76\u9891\u7387");
    model.param().set("omega0", "2*pi*f0", "\u7814\u7a76\u89d2\u9891\u7387");
    model.param().set("dvisc", "55[um]*sqrt(100[Hz]/f0)", "f0 \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("c0", "1484[m/s]", "\u58f0\u901f");
    model.param().set("rho0", "1000[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("k0", "2*pi/lam0", "f0 \u7684\u6ce2\u6570");
    model.param().set("U0", "1[mm/s]", "z \u5411\u901f\u5ea6\u5e45\u503c");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "a_s");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", "a_tot");
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "a_tot - a_ta", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("b", "a_s*k0");
    model.component("comp1").variable("var1").descr("b", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("R0", "sqrt(r^2 + z^2)");
    model.component("comp1").variable("var1").descr("R0", "\u79bb\u539f\u70b9\u7684\u5f84\u5411\u8ddd\u79bb");
    model.component("comp1").variable("var1")
         .set("phi_an", "U0*a_s^3/R0^3*z*exp(i*k0*(R0 - a_s))*(i*k0*R0 - 1)/(2 - b^2 -2*i*b)");
    model.component("comp1").variable("var1").descr("phi_an", "\u901f\u5ea6\u52bf\uff08\u6e10\u8fd1\uff09");
    model.component("comp1").variable("var1").set("p_an", "i*omega0*rho0*phi_an");
    model.component("comp1").variable("var1").descr("p_an", "\u58f0\u538b\uff08\u6e10\u8fd1\uff09");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 3);
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");

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

    model.component("comp1").physics("ta").prop("ReferencePressure").set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("ta").prop("EquationSettings").set("AdiabaticFormulation", true);
    model.component("comp1").physics("ta").create("velt1", "VelocityThermoacoustic", 1);
    model.component("comp1").physics("ta").feature("velt1").selection().set(8, 9);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 0);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 2);
    model.component("comp1").physics("ta").feature("velt1").setIndex("u0", "U0", 2);
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "Viscous");
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 1);
    model.component("comp1").physics("acpr").feature("tvb1").selection().set(8, 9);
    model.component("comp1").physics("acpr").feature("tvb1").set("MechanicalCondition", "Velocity");
    model.component("comp1").physics("acpr").feature("tvb1").set("vel", new String[]{"0", "0", "U0"});
    model.component("comp1").physics("acpr").feature("tvb1").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").mesh("mesh1").contribute("physics/acpr", false);

    model.component("comp1").physics("ta").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("ta").prop("MeshControl").set("nperlambda", 12);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurve", 0.03);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 8);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhtot", "2*pi*dvisc");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().set(2);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u77ac\u65f6\u5c40\u90e8\u901f\u5ea6 (dB)");
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().set(2);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "10*log10(abs(ta.v_inst/U0))[dB]");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "a_s/sqrt(2)", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "a_s/sqrt(2)", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "0.5*a_ta/sqrt(2)", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.5*a_ta/sqrt(2)", 1, 1);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "a_s/sqrt(2)", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "a_s/sqrt(2)", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "(a_s+20*dvisc)/sqrt(2)", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "(a_s+20*dvisc)/sqrt(2)", 1, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b vs. \u8ddd\u79bb");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "|r| (mm)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u58f0\u538b (Pa)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "R0");
    model.result("pg3").feature("lngr1").set("xdataunit", "mm");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "Thermoviscous Acoustics", 0);
    model.result("pg3").feature("lngr1").setIndex("legends", "\u70ed\u9ecf\u6027\u58f0\u5b66", 0);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "p_an");
    model.result("pg3").feature("lngr2")
         .setIndex("legends", "\u89e3\u6790\u89e3\uff08\u6e10\u8fd1/\u7edd\u70ed\uff09", 0);
    model.result("pg3").feature().duplicate("lngr3", "lngr2");
    model.result("pg3").run();
    model.result("pg3").feature("lngr3").set("expr", "acpr.p_t");
    model.result("pg3").feature("lngr3").setIndex("legends", "\u5177\u6709 BLI \u7684\u58f0\u538b", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u5411\u901f\u5ea6 vs. \u8ddd\u79bb");
    model.result("pg4").set("data", "cln2");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "|r - a<sub>s</sub>| / \\delta<sub>v</sub> (1)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8f74\u5411\u901f\u5ea6\uff1aw (m/s)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "w");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "(R0-a_s)/dvisc");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u70ed\u9ecf\u6027\u58f0\u5b66", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "U0");
    model.result("pg4").feature("lngr2")
         .setIndex("legends", "\u6307\u5b9a\u8f74\u5411\u901f\u5ea6\uff1aU<sub>0</sub>", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").set("expr", "acpr.vz");
    model.result("pg4").feature("lngr3").setIndex("legends", "\u5177\u6709 BLI \u7684\u538b\u529b\u58f0\u5b66", 0);
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u6c34\u4e2d\u7684\u632f\u52a8\u9897\u7c92");

    model
         .description("\u672c\u6559\u7a0b\u8ba8\u8bba\u4e00\u4e2a\u5728\u6c34\u4e2d\u632f\u52a8\u7684\u534a\u7403\u5f62\u5c0f\u9897\u7c92\uff0c\u5176\u632f\u52a8\u4f1a\u5728\u6d41\u4f53\u4e2d\u4ea7\u751f\u58f0\u6ce2\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u70ed\u9ecf\u6027\u58f0\u5b66\u6a21\u578b\u6765\u6c42\u89e3\u6d41\u4f53\u4e2d\u7684\u58f0\uff08\u53ef\u538b\u7f29\uff09\u6ce2\u3002\u5728\u5efa\u6a21\u8fc7\u7a0b\u4e2d\uff0c\u8fd8\u4f7f\u7528\u4e86\u538b\u529b\u58f0\u5b66\u4e2d\u7684\u201c\u70ed\u9ecf\u6027\u8fb9\u754c\u5c42\u963b\u6297\u201d\u6761\u4ef6\u3002\u901a\u8fc7\u5c06\u6570\u503c\u89e3\u8fdb\u884c\u76f8\u4e92\u6bd4\u8f83\uff0c\u5e76\u5c06\u5176\u4e0e\u89e3\u6790\u6e10\u8fd1\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\uff0c\u6765\u9a8c\u8bc1\u6570\u503c\u89e3\u7684\u6b63\u786e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("vibrating_particle_water.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
