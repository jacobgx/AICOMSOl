/*
 * frequency_selective_surface_simulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:31 by COMSOL 6.3.0.290. */
public class frequency_selective_surface_simulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("theta", "0[deg]");
    model.param().descr("theta", "Elevation angle");
    model.param().set("phi", "0[deg]");
    model.param().descr("phi", "Azimuth angle");
    model.param().set("f0", "4.6[GHz]");
    model.param().descr("f0", "Input frequency [GHz]");
    model.param().set("lda0", "c_const/f0");
    model.param().descr("lda0", "Wavelength, free space");
    model.param().set("o_type", "3");
    model.param().descr("o_type", "Object type");
    model.param().set("w_ring", "1.5[mm]");
    model.param().descr("w_ring", "Ring width");
    model.param().set("r_ring", "4.25[mm]");
    model.param().descr("r_ring", "Ring radius");
    model.param().set("g_ring", "1[mm]");
    model.param().descr("g_ring", "Ring gap size");
    model.param().set("periodicity", "15[mm]");
    model.param().descr("periodicity", "Cell periodicity");
    model.param().set("t_sub", "2[mm]");
    model.param().descr("t_sub", "Substrate thickness");
    model.param().set("rc_length", "10[mm]");
    model.param().descr("rc_length", "Rectangle length");
    model.param().set("rc_width", "1[mm]");
    model.param().descr("rc_width", "Rectangle width");
    model.param().set("cr_length1", "10[mm]");
    model.param().descr("cr_length1", "Cross length 1");
    model.param().set("cr_width1", "1[mm]");
    model.param().descr("cr_width1", "Cross width 1");
    model.param().set("cr_length2", "10[mm]");
    model.param().descr("cr_length2", "Cross length 2");
    model.param().set("cr_width2", "1[mm]");
    model.param().descr("cr_width2", "Cross width 2");
    model.param().set("r_circle", "4[mm]");
    model.param().descr("r_circle", "Circle radius");
    model.param().set("bw", "1[GHz]");
    model.param().descr("bw", "Bandwidth");
    model.param().set("s_eps", "2.1");
    model.param().descr("s_eps", "Substrate permittivity");
    model.param().set("s_mu", "1");
    model.param().descr("s_mu", "Substrate permeability");
    model.param().set("rot", "0[deg]");
    model.param().descr("rot", "Rotation angle");
    model.param().set("nvalues", "21");
    model.param().descr("nvalues", "The number of frequency");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("Ring");
    model.geom("part1").create("c1", "Circle");
    model.geom("part1").feature("c1").set("r", "r_ring+w_ring/2");
    model.geom("part1").run("c1");
    model.geom("part1").create("c2", "Circle");
    model.geom("part1").feature("c2").set("r", "r_ring-w_ring/2");
    model.geom("part1").run("c2");
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("Split ring");
    model.geom("part2").feature().copy("c1", "part1/c1");
    model.geom("part2").feature().copy("c2", "part1/c2");
    model.geom("part2").run("c2");
    model.geom("part2").create("r1", "Rectangle");
    model.geom("part2").feature("r1").set("size", new String[]{"r_ring+w_ring/2", "1"});
    model.geom("part2").feature("r1").setIndex("size", "g_ring", 1);
    model.geom("part2").feature("r1").set("pos", new String[]{"(r_ring+w_ring/2)/2", "0"});
    model.geom("part2").feature("r1").set("base", "center");
    model.geom("part2").run("r1");
    model.geom("part2").create("dif1", "Difference");
    model.geom("part2").feature("dif1").selection("input").set("c1");
    model.geom("part2").feature("dif1").selection("input2").set("c2", "r1");
    model.geom("part2").run("dif1");
    model.geom("part2").create("rot1", "Rotate");
    model.geom("part2").feature("rot1").selection("input").set("dif1");
    model.geom("part2").feature("rot1").set("rot", "rot");
    model.geom("part2").run("rot1");
    model.geom().create("part3", "Part", 2);
    model.geom("part3").label("Rectangle");
    model.geom("part3").create("r1", "Rectangle");
    model.geom("part3").feature("r1").set("size", new String[]{"rc_length", "rc_width"});
    model.geom("part3").feature("r1").set("base", "center");
    model.geom("part3").run("r1");
    model.geom("part3").create("rot1", "Rotate");
    model.geom("part3").feature("rot1").selection("input").set("r1");
    model.geom("part3").feature("rot1").set("rot", "rot");
    model.geom("part3").run("rot1");
    model.geom().create("part4", "Part", 2);
    model.geom("part4").label("Cross");
    model.geom("part4").create("r1", "Rectangle");
    model.geom("part4").feature("r1").set("size", new String[]{"cr_length1", "cr_width1"});
    model.geom("part4").feature("r1").set("base", "center");
    model.geom("part4").run("r1");
    model.geom("part4").create("r2", "Rectangle");
    model.geom("part4").feature("r2").set("size", new String[]{"cr_width2", "cr_length2"});
    model.geom("part4").feature("r2").set("base", "center");
    model.geom("part4").run("r2");
    model.geom("part4").create("uni1", "Union");
    model.geom("part4").feature("uni1").selection("input").set("r1", "r2");
    model.geom("part4").feature("uni1").set("intbnd", false);
    model.geom("part4").run("uni1");
    model.geom("part4").create("rot1", "Rotate");
    model.geom("part4").feature("rot1").selection("input").set("uni1");
    model.geom("part4").feature("rot1").set("rot", "rot");
    model.geom("part4").run("rot1");
    model.geom().create("part5", "Part", 2);
    model.geom("part5").label("Circle");
    model.geom("part5").create("c1", "Circle");
    model.geom("part5").feature("c1").set("r", "r_circle");
    model.geom("part5").run("c1");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"periodicity", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "periodicity", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "lda0*0.8", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"periodicity", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "periodicity", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "t_sub", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-t_sub/2"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"periodicity", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "periodicity", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "lda0*1.4", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if1").set("condition", "o_type==1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("part", "part5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("elseif1").set("condition", "o_type==2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("elseif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("elseif2", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("elseif2").set("condition", "o_type==3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("elseif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi3").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("elseif3", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("elseif3").set("condition", "o_type==4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("elseif3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("else1", "Else");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("else1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi5").set("part", "part4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("endif1");
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
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
    model.component("comp1").material("mat2").label("Substrate");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"s_eps"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"s_mu"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("k_x", "emw.k0*sin(theta)*cos(phi)");
    model.component("comp1").variable("var1").descr("k_x", "kx for incident wave");
    model.component("comp1").variable("var1").set("k_y", "emw.k0*sin(theta)*sin(phi)");
    model.component("comp1").variable("var1").descr("k_y", "ky for incident wave");
    model.component("comp1").variable("var1").set("k_z", "emw.k0*cos(theta)");
    model.component("comp1").variable("var1").descr("k_z", "kz for incident wave");

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(12);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("PEC");
    model.component("comp1").selection("sel1").set(12);

    model.component("comp1").physics("emw").feature("pec2").selection().named("sel1");
    model.component("comp1").physics("emw").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("emw").feature("pc1").selection().set(4, 7, 10, 24, 25, 26);
    model.component("comp1").physics("emw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("emw").feature("pc1").set("kFloquet", new String[]{"k_x", "k_y", "0"});
    model.component("comp1").physics("emw").feature().duplicate("pc2", "pc1");
    model.component("comp1").physics("emw").feature("pc2").selection().set(1, 13, 23, 27);
    model.component("comp1").physics("emw").feature().duplicate("pc3", "pc2");
    model.component("comp1").physics("emw").feature("pc3").selection().set(5, 8, 11, 18, 19, 20);
    model.component("comp1").physics("emw").feature().duplicate("pc4", "pc3");
    model.component("comp1").physics("emw").feature("pc4").selection().set(2, 14, 17, 21);
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("emw").feature("port1").set("SlitType", "DomainBacked");
    model.component("comp1").physics("emw").feature("port1").selection().set(15);
    model.component("comp1").physics("emw").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("emw").feature("port1")
         .set("E0", new String[]{"exp(-i*k_x*x)*exp(-i*k_y*y)[V/m]", "0", "0"});
    model.component("comp1").physics("emw").feature("port1").set("beta", "abs(k_z)");
    model.component("comp1").physics("emw").feature().duplicate("port2", "port1");
    model.component("comp1").physics("emw").feature("port2").selection().set(6);
    model.component("comp1").physics("emw").feature("port2").set("PortExcitation", "off");
    model.component("comp1").physics("emw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("emw").feature("sctr1").selection().set(3, 16);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(5);
    model.component("comp1").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml1").set("typicalWavelength", "2*pi/abs(k_z)");
    model.component("comp1").coordSystem().duplicate("pml2", "pml1");
    model.component("comp1").coordSystem("pml2").selection().set(1);

    model.component("comp1").physics().create("emw2", "ElectromagneticWaves", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/emw2", true);

    model.component("comp1").physics("emw2").selection().set(3);

    model.study("std1").feature("freq").set("plist", "range(f0-bw/2,(f0+bw/2-(f0-bw/2))/(nvalues-1),f0+bw/2)");
    model.study("std1").feature("freq").setSolveFor("/physics/emw2", false);
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").setSolveFor("/physics/emw2", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", false);
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.component("comp1").physics("emw").prop("MeshControl").set("SizeControlParameter", "UserDefined");
    model.component("comp1").physics("emw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumElementSize", "lda0/10");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Electric Field (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S-parameter (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S-parameter (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("Smith Plot (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1").set("title", "Reflection Graph: S-parameter, Color: Frequency (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("Electric Field, Logarithmic (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(12);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(12);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection()
         .set(1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 17, 18, 19, 20, 21, 23, 24, 25, 26, 27);
    model.result("pg4").feature("surf2").set("colortable", "Prism");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.7);
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().set(9, 22);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view8", "geom1");
    model.component("comp1").view("view8").camera()
         .set("position", new double[]{-66.10433827275816, -88.13911769701087, 66.10433827275816});
    model.component("comp1").view("view8").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view8").camera().set("zoomanglefull", 48.17861080169678);

    model.result("pg4").set("view", "view8");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(3);
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol2");
    model.result().dataset("dset3").label("Metal");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("sel1");
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").label("Dielectric");
    model.result().dataset("dset4").set("solution", "sol2");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(7, 8, 9, 19, 22, 25);
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").label("Array 3D Metal");
    model.result().dataset("arr1").set("data", "dset3");
    model.result().dataset("arr1").set("fullsize", new int[]{10, 10, 1});
    model.result().dataset().create("arr2", "Array3D");
    model.result().dataset("arr2").label("Array 3D Dielectric");
    model.result().dataset("arr2").set("data", "dset4");
    model.result().dataset("arr2").set("fullsize", new int[]{10, 10, 1});
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().set(2, 3, 4);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "dset5");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Virtual array view");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").setIndex("expr", 0, 0);
    model.result("pg4").feature("arwv1").set("expr", new String[]{"0", "0", "-1"});
    model.result("pg4").feature("arwv1").set("xnumber", 1);
    model.result("pg4").feature("arwv1").set("ynumber", 1);
    model.result("pg4").feature("arwv1").set("znumber", 1);
    model.result("pg4").feature("arwv1").set("arrowbase", "head");
    model.result("pg4").feature("arwv1").set("scaleactive", true);
    model.result("pg4").feature("arwv1").set("scale", 20);
    model.result("pg4").feature("arwv1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("arwv1").feature("def1").set("expr", new String[]{"periodicity*0", "", ""});
    model.result("pg4").feature("arwv1").feature("def1").setIndex("expr", "periodicity*0", 1);
    model.result("pg4").feature("arwv1").feature("def1").setIndex("expr", "t_sub/2+1[mm]", 2);
    model.result("pg4").feature("arwv1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("arwv1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("arwv2", "arwv1");
    model.result("pg4").run();
    model.result("pg4").feature("arwv2").set("expr", new String[]{"1", "0", "0"});
    model.result("pg4").feature("arwv2").set("arrowbase", "tail");
    model.result("pg4").feature("arwv2").set("scale", 5);
    model.result("pg4").feature("arwv2").set("color", "blue");
    model.result("pg4").run();
    model.result("pg4").feature("arwv2").feature("def1").setIndex("expr", "t_sub/2+21[mm]", 2);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("data", "arr1");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "custom");
    model.result("pg4").feature("surf1")
         .set("customcolor", new double[]{0.7803921699523926, 0.4588235318660736, 0.027450980618596077});
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "rosegold");
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("data", "arr2");
    model.result("pg4").feature("surf2").set("expr", "1");
    model.result("pg4").feature("surf2").set("coloring", "uniform");
    model.result("pg4").feature("surf2").set("color", "custom");
    model.result("pg4").feature("surf2")
         .set("customcolor", new double[]{0.9803921580314636, 0.9411764740943909, 0.9019607901573181});

    model.view("view9").label("View 3D array");
    model.view("view9").set("showgrid", false);
    model.view("view9").camera().set("zoomanglefull", 42.84846496582031);
    model.view("view9").camera().setIndex("position", -29.665245056152344, 0);
    model.view("view9").camera().setIndex("position", -56.52446746826172, 1);
    model.view("view9").camera().setIndex("position", 44.58572769165039, 2);
    model.view("view9").camera().set("target", new double[]{554.8160400390625, 0, -1});
    model.view("view9").camera().setIndex("target", 833.3695678710938, 1);
    model.view("view9").camera().setIndex("target", -303.78875732421875, 2);
    model.view("view9").camera().setIndex("up", 0.1354120671749115, 0);
    model.view("view9").camera().setIndex("up", 0.2828001379966736, 1);
    model.view("view9").camera().setIndex("up", 0.9495759606361389, 2);
    model.view("view9").camera().set("rotationpoint", new double[]{67.5, 67.5, 9.5});
    model.view("view9").camera().setIndex("viewoffset", -0.040856845676898956, 0);
    model.view("view9").camera().setIndex("viewoffset", 0.25924259424209595, 1);
    model.component("comp1").view().create("view10", "geom1");
    model.component("comp1").view("view10").set("renderwireframe", true);
    model.component("comp1").view().duplicate("view11", "view10");
    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 5);
    model.component("comp1").view("view10").hideEntities().create("hide1");
    model.component("comp1").view("view10").hideEntities("hide1").set(1, 2, 4, 5);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("Field plot1");
    model.result("pg5").set("view", "view10");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickzmethod", "coord");
    model.result("pg5").feature("slc1").set("colortable", "Thermal");
    model.result("pg5").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg5").feature("slc1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("slc1").feature("def1").set("expr", new String[]{"0", "0", "-emw.normE"});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("Field plot2");
    model.result("pg6").set("view", "view11");
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", "20*log10(emw.normE)");
    model.result("pg6").feature("con1").set("contourtype", "filled");
    model.result("pg6").feature("con1").set("data", "cpl1");
    model.result("pg6").feature("con1").set("legendtype", "filled");
    model.result("pg6").run();
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linemarker", "point");
    model.result("pg2").feature("glob1").setIndex("descr", "S11dB", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "S21dB", 1);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");

    model.title("\u9891\u7387\u9009\u62e9\u8868\u9762\u6a21\u62df\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u8bbe\u8ba1\u7528\u4e8e\u667a\u80fd\u624b\u673a\u7b49\u5c0f\u5c4f\u5e55\u7684 App\n\u2022 \u7528\u6237\u754c\u9762\u5bfc\u822a\uff08\u5177\u6709\u7f51\u7ad9\u4e0a\u5e38\u7528\u7684\u9876\u90e8\u83dc\u5355\uff09\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u4f7f\u7528\u6750\u6599\u6e32\u67d3\u5c06\u51e0\u4f55\u7684\u5468\u671f\u6027\u53ef\u89c6\u5316\n\u2022 \u5c5e\u6027\u672a\u66f4\u65b0\u65f6\u5728\u56fe\u6807\u4e0a\u663e\u793a\u8b66\u544a\u6d88\u606f\n\u2022 \u8ba1\u7b97\u5b8c\u6210\u540e\u53d1\u9001\u9644\u5e26\u62a5\u544a\u7684\u7535\u5b50\u90ae\u4ef6\n\n\u9891\u7387\u9009\u62e9\u8868\u9762 (FSS) \u662f\u4e00\u79cd\u4ea7\u751f\u5e26\u901a\u6216\u5e26\u963b\u9891\u7387\u54cd\u5e94\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u7528\u4e8e\u8fc7\u6ee4\u6216\u963b\u6b62\u5c04\u9891\u3001\u5fae\u6ce2\u6216\uff08\u4e8b\u5b9e\u4e0a\u662f\uff09\u4efb\u4f55\u7535\u78c1\u6ce2\u9891\u7387\u3002\u4f8b\u5982\uff0c\u5fae\u6ce2\u7089\u95e8\u4e0a\u5c31\u6709\u8fd9\u6837\u7684\u9009\u62e9\u8868\u9762\uff0c\u901a\u8fc7\u8fd9\u4e9b\u8868\u9762\uff0c\u60a8\u65e0\u9700\u4eb2\u81ea\u52a0\u70ed\u5c31\u80fd\u770b\u5230\u98df\u7269\u7684\u52a0\u70ed\u8fc7\u7a0b\u3002\n\n\u8be5 App \u6a21\u62df\u4e00\u4e2a\u4ece\u5185\u7f6e\u57fa\u672c\u5355\u5143\u7c7b\u578b\u4e2d\u9009\u62e9\u7684\u7528\u6237\u6307\u5b9a\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u5176\u4e2d\u63d0\u4f9b FSS \u4eff\u771f\u4e2d\u5e38\u7528\u7684\u4e94\u79cd\u57fa\u672c\u5355\u5143\u7c7b\u578b\uff0c\u4ee5\u53ca\u5728\u4e00\u4e2a\u56fa\u5b9a\u4f20\u64ad\u65b9\u5411\uff08\u5728 FSS \u5782\u76f4\u5165\u5c04\uff09\u7684\u4e24\u4e2a\u9884\u5b9a\u4e49\u7684\u6781\u5316\u3002\u672c\u4f8b\u5206\u6790\u53cd\u5c04\u8c31\u548c\u900f\u5c04\u8c31\u3001\u57fa\u672c\u5355\u5143\u9876\u9762\u7684\u7535\u573a\u6a21\uff0c\u4ee5\u53ca\u57fa\u672c\u5355\u5143\u57df\u4e2d\u5782\u76f4\u622a\u9762\u4e0a\u663e\u793a\u7684 dB \u523b\u5ea6\u7684\u7535\u573a\u6a21\u3002\n\n\u60a8\u53ef\u4ee5\u66f4\u6539\u6781\u5316\u3001\u4e2d\u5fc3\u9891\u7387\u3001\u5e26\u5bbd\u3001\u9891\u7387\u6570\u3001\u57fa\u677f\u539a\u5ea6\u53ca\u5176\u6750\u6599\u5c5e\u6027\u3001\u57fa\u672c\u5355\u5143\u7c7b\u578b\uff08\u5706\u3001\u73af\u548c\u5f00\u53e3\u73af\u7b49\uff09\u53ca\u5176\u51e0\u4f55\u53c2\u6570\uff0c\u5305\u62ec\u5468\u671f\u6027\uff08\u5355\u5143\u5927\u5c0f\uff09\u3002");

    model.label("frequency_selective_surface_simulator.mph");

    model.result("pg2").run();

    model.setExpectedComputationTime("44 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("filename", "user:///frequency_selective_surface_simulator");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u9891\u7387\u9009\u62e9\u8868\u9762 (FSS) \u662f\u4e00\u79cd\u4ea7\u751f\u5e26\u901a\u6216\u5e26\u963b\u9891\u7387\u54cd\u5e94\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u672c App \u9009\u62e9\u5185\u7f6e\u57fa\u672c\u5355\u5143\u7c7b\u578b\u751f\u6210\u5468\u671f\u6027\u7ed3\u6784\uff0c\u5206\u6790\u5185\u5bb9\u5305\u62ec\u53cd\u5c04\u8c31\u548c\u900f\u5c04\u8c31\u3001\u57fa\u672c\u5355\u5143\u9876\u9762\u7684\u7535\u573a\u6a21\uff0c\u4ee5\u53ca\u57fa\u672c\u5355\u5143\u57df\u4e2d\u5782\u76f4\u622a\u9762\u4e0a\u663e\u793a\u7684 dB \u523b\u5ea6\u7684\u7535\u573a\u6a21\u3002\u5176\u4e2d\u63d0\u4f9b\u4e86\u4e94\u79cd\u5e38\u89c1\u7684\u57fa\u672c\u5355\u5143\u7c7b\u578b\uff0c\u5404\u81ea\u5177\u6709\u4e24\u79cd\u9884\u5b9a\u4e49\u7684\u6781\u5316\u65b9\u5f0f\uff0c\u5e76\u6cbf\u6cd5\u7ebf\u5165\u5c04\u65b9\u5411\u4f20\u64ad\u3002");
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u53c2\u6570 1");
    model.result().report("rpt1").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u7ec4\u4ef6 1");
    model.result().report("rpt1").feature("sec2").feature().create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u51e0\u4f55 1");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("mat1", "Material");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u7535\u78c1\u6ce2\uff0c\u9891\u57df");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("phys1", "Physics");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("phys1").set("includeselection", true);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("phys1").set("includesettings", true);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("phys1").set("includefeaturetable", false);
    model.result().report("rpt1").feature("sec2").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec4").label("\u7f51\u683c 1");
    model.result().report("rpt1").feature("sec2").feature("sec4").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec4").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").set("templatesource", "intermediate");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").label("S \u53c2\u6570 (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2")
         .label("\u865a\u62df\u9635\u5217\u89c6\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("pg1")
         .set("noderef", "pg4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3")
         .label("\u7535\u573a\u6a21\uff08\u6c34\u5e73\uff09");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4")
         .label("\u7535\u573a\u6a21\uff08\u5782\u76f4\uff0cdB\uff09");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec4").feature("pg1")
         .set("noderef", "pg6");

    model.title("\u9891\u7387\u9009\u62e9\u8868\u9762\u6a21\u62df\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u8bbe\u8ba1\u7528\u4e8e\u667a\u80fd\u624b\u673a\u7b49\u5c0f\u5c4f\u5e55\u7684 App\n\u2022 \u7528\u6237\u754c\u9762\u5bfc\u822a\uff08\u5177\u6709\u7f51\u7ad9\u4e0a\u5e38\u7528\u7684\u9876\u90e8\u83dc\u5355\uff09\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u4f7f\u7528\u6750\u6599\u6e32\u67d3\u5c06\u51e0\u4f55\u7684\u5468\u671f\u6027\u53ef\u89c6\u5316\n\u2022 \u5c5e\u6027\u672a\u66f4\u65b0\u65f6\u5728\u56fe\u6807\u4e0a\u663e\u793a\u8b66\u544a\u6d88\u606f\n\u2022 \u8ba1\u7b97\u5b8c\u6210\u540e\u53d1\u9001\u9644\u5e26\u62a5\u544a\u7684\u7535\u5b50\u90ae\u4ef6\n\n\u9891\u7387\u9009\u62e9\u8868\u9762 (FSS) \u662f\u4e00\u79cd\u4ea7\u751f\u5e26\u901a\u6216\u5e26\u963b\u9891\u7387\u54cd\u5e94\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u7528\u4e8e\u8fc7\u6ee4\u6216\u963b\u6b62\u5c04\u9891\u3001\u5fae\u6ce2\u6216\uff08\u4e8b\u5b9e\u4e0a\u662f\uff09\u4efb\u4f55\u7535\u78c1\u6ce2\u9891\u7387\u3002\u4f8b\u5982\uff0c\u5fae\u6ce2\u7089\u95e8\u4e0a\u5c31\u6709\u8fd9\u6837\u7684\u9009\u62e9\u8868\u9762\uff0c\u901a\u8fc7\u8fd9\u4e9b\u8868\u9762\uff0c\u60a8\u65e0\u9700\u4eb2\u81ea\u52a0\u70ed\u5c31\u80fd\u770b\u5230\u98df\u7269\u7684\u52a0\u70ed\u8fc7\u7a0b\u3002\n\n\u8be5 App \u6a21\u62df\u4e00\u4e2a\u4ece\u5185\u7f6e\u57fa\u672c\u5355\u5143\u7c7b\u578b\u4e2d\u9009\u62e9\u7684\u7528\u6237\u6307\u5b9a\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u5176\u4e2d\u63d0\u4f9b FSS \u4eff\u771f\u4e2d\u5e38\u7528\u7684\u4e94\u79cd\u57fa\u672c\u5355\u5143\u7c7b\u578b\uff0c\u4ee5\u53ca\u5728\u4e00\u4e2a\u56fa\u5b9a\u4f20\u64ad\u65b9\u5411\uff08\u5728 FSS \u5782\u76f4\u5165\u5c04\uff09\u7684\u4e24\u4e2a\u9884\u5b9a\u4e49\u7684\u6781\u5316\u3002\u672c\u4f8b\u5206\u6790\u53cd\u5c04\u8c31\u548c\u900f\u5c04\u8c31\u3001\u57fa\u672c\u5355\u5143\u9876\u9762\u7684\u7535\u573a\u6a21\uff0c\u4ee5\u53ca\u57fa\u672c\u5355\u5143\u57df\u4e2d\u5782\u76f4\u622a\u9762\u4e0a\u663e\u793a\u7684 dB \u523b\u5ea6\u7684\u7535\u573a\u6a21\u3002\n\n\u60a8\u53ef\u4ee5\u66f4\u6539\u6781\u5316\u3001\u4e2d\u5fc3\u9891\u7387\u3001\u5e26\u5bbd\u3001\u9891\u7387\u6570\u3001\u57fa\u677f\u539a\u5ea6\u53ca\u5176\u6750\u6599\u5c5e\u6027\u3001\u57fa\u672c\u5355\u5143\u7c7b\u578b\uff08\u5706\u3001\u73af\u548c\u5f00\u53e3\u73af\u7b49\uff09\u53ca\u5176\u51e0\u4f55\u53c2\u6570\uff0c\u5305\u62ec\u5468\u671f\u6027\uff08\u5355\u5143\u5927\u5c0f\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("frequency_selective_surface_simulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
