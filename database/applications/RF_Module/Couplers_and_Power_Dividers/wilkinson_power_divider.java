/*
 * wilkinson_power_divider.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:32 by COMSOL 6.3.0.290. */
public class wilkinson_power_divider {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Couplers_and_Power_Dividers");

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
    model.study("std1").feature("freq").set("plist", "range(2[GHz],0.1[GHz],4[GHz])");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_ring", "8.5[mm]", "\u534a\u5f84\uff0c\u5fae\u5e26\u7ebf\u73af");
    model.param().set("w_subs", "30[mm]", "\u5bbd\u5ea6\uff0c\u57fa\u677f");
    model.param().set("l_subs", "26[mm]", "\u957f\u5ea6\uff0c\u57fa\u677f");
    model.param().set("r_inner", "0.635[mm]", "\u534a\u5f84\uff0c\u5185\u90e8\u540c\u8f74\u7ebf");
    model.param().set("r_outer", "2.05[mm]", "\u534a\u5f84\uff0c\u5916\u90e8\u540c\u8f74\u7ebf");
    model.param().set("l_sma", "8[mm]", "\u957f\u5ea6\uff0cSMA");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_subs", "l_subs", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", 1.524, 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, 0, -0.762});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u5c01\u88c5");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"w_subs", "l_subs", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", 20, 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{0, 0, 2});
    model.component("comp1").geom("geom1").run("blk2");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").label("\u5916\u90e8\u73af");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_ring");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").label("\u5185\u90e8\u73af");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "r_ring-1.9");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").label("\u526a\u5207\u73af");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{2, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{-1, -9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").label("\u96c6\u603b\u5143\u4ef6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{2, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{-1, -8});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{3.2, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{0, 10.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{3.2, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{-7, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new int[]{-7, -12});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new double[]{3.2, 6});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new double[]{-8.6, -11});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("rot", -28);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r4", "r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("dif1", "mir1", "r3", "r4", "r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.geom()
         .load(new String[]{"part1"}, "RF_Module\\Connectors\\SMA_Connectors\\connector_sma_flange4.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_dielectric", "8[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_pin", "1[mm]");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "l_subs/2", "0.635"});
    model.component("comp1").geom("geom1").feature("pi1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "-7,7");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u87ba\u9489");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 8);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{-12, -10, -8});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u87ba\u9489\u5934");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 1.5);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{-12, -10, 0});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{2, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{24, 20, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "blk2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(7, 8, 10);

    model.component("comp1").physics("emw").create("trans1", "TransitionBoundaryCondition", 2);
    model.component("comp1").physics("emw").feature("trans1").selection().set(6, 80);
    model.component("comp1").physics("emw").feature("trans1").set("d", "35[um]");
    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().named("geom1_pi1_sel2");
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(160);
    model.component("comp1").physics("emw").feature("lport1").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(75);
    model.component("comp1").physics("emw").feature("lport2").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").create("lport3", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport3").selection().set(242);
    model.component("comp1").physics("emw").feature("lport3").set("PortType", "Coaxial");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("emw").create("lelement1", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement1").selection().set(164);
    model.component("comp1").physics("emw").feature("lelement1").set("Zelement", "100[ohm]");

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().set(6, 80);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u57fa\u677f");
    model.component("comp1").material("mat3").selection().set(2);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"3.38"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("PTFE");
    model.component("comp1").material("mat4").selection().named("geom1_pi1_sel3");
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S31"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
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
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 83, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 113, 115, 118, 119, 120, 121, 122, 123, 124, 125, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 162, 163, 165, 166, 178, 185, 188, 189, 190, 191, 192, 193, 194, 195, 196, 198, 199, 200, 201, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 247, 248, 249, 250, 253, 263, 268, 269, 270, 271, 272, 273, 274, 275, 276, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 75, 83, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 113, 115, 118, 119, 120, 121, 122, 123, 124, 125, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 160, 162, 163, 165, 166, 178, 185, 188, 189, 190, 191, 192, 193, 194, 195, 196, 198, 199, 200, 201, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 242, 247, 248, 249, 250, 253, 263, 268, 269, 270, 271, 272, 273, 274, 275, 276, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 83, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 113, 115, 118, 119, 120, 121, 122, 123, 124, 125, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 162, 163, 165, 166, 178, 185, 188, 189, 190, 191, 192, 193, 194, 195, 196, 198, 199, 200, 201, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 247, 248, 249, 250, 253, 263, 268, 269, 270, 271, 272, 273, 274, 275, 276, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(6, 80);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 75, 83, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 113, 115, 118, 119, 120, 121, 122, 123, 124, 125, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 160, 162, 163, 165, 166, 178, 185, 188, 189, 190, 191, 192, 193, 194, 195, 196, 198, 199, 200, 201, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 242, 247, 248, 249, 250, 253, 263, 268, 269, 270, 271, 272, 273, 274, 275, 276, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);

    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(6, 80);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "rosegold");
    model.result("pg4").feature("surf2").set("expr", "1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(26, 27, 28, 73, 74, 76, 77, 81, 82, 84, 85, 86, 87, 91, 92, 94, 95, 96, 97, 107, 108, 109, 110, 112, 114, 116, 117, 155, 156, 158, 159, 167, 168, 169, 170, 172, 173, 174, 175, 179, 180, 181, 182, 183, 184, 186, 187, 197, 202, 203, 204, 240, 241, 243, 244, 251, 252, 254, 255, 256, 257, 261, 262, 264, 265, 266, 267, 277, 278, 279, 280, 281);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 75, 83, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 113, 115, 118, 119, 120, 121, 122, 123, 124, 125, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 160, 162, 163, 165, 166, 178, 185, 188, 189, 190, 191, 192, 193, 194, 195, 196, 198, 199, 200, 201, 205, 206, 207, 208, 209, 210, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 242, 247, 248, 249, 250, 253, 263, 268, 269, 270, 271, 272, 273, 274, 275, 276, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);

    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection()
         .set(26, 27, 28, 73, 74, 76, 77, 81, 82, 84, 85, 86, 87, 91, 92, 94, 95, 96, 97, 107, 108, 109, 110, 112, 114, 116, 117, 155, 156, 158, 159, 167, 168, 169, 170, 172, 173, 174, 175, 179, 180, 181, 182, 183, 184, 186, 187, 197, 202, 203, 204, 240, 241, 243, 244, 251, 252, 254, 255, 256, 257, 261, 262, 264, 265, 266, 267, 277, 278, 279, 280, 281);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf3").feature("mtrl1").set("family", "chrome");
    model.result("pg4").feature("surf3").set("expr", "1");
    model.result("pg4").create("surf4", "Surface");
    model.result("pg4").feature("surf4").set("expr", "emw.normE");
    model.result("pg4").feature("surf4").create("sel1", "Selection");
    model.result("pg4").feature("surf4").feature("sel1").selection()
         .set(9, 72, 78, 79, 88, 89, 90, 111, 126, 151, 157, 161, 164, 171, 176, 177, 211, 239, 245, 246, 258, 259, 260);
    model.result("pg4").feature("surf4").set("colortable", "Dipole");
    model.result("pg4").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf4").create("tran1", "Transparency");
    model.result("pg4").feature("surf4").feature("tran1").set("transparency", 0.3);
    model.result("pg4").create("surf5", "Surface");
    model.result("pg4").feature("surf5").set("expr", "emw.normE");
    model.result("pg4").feature("surf5").create("sel1", "Selection");
    model.result("pg4").feature("surf5").feature("sel1").selection().set(75, 160, 242);
    model.result("pg4").feature("surf5").set("colortable", "Dipole");
    model.result("pg4").feature("surf5").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf5").create("tran1", "Transparency");
    model.result("pg4").feature("surf5").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-46.65638799252718, -62.20851732336957, 46.32508319357167});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 31.819489002227783);

    model.result("pg4").set("view", "view4");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").active(false);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_pi1_sel2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("surf1").feature("mtrl1").set("family", "aluminumanodized");
    model.result("pg1").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u96c6\u603b\u7aef\u53e3 1");
    model.component("comp1").selection("sel1").set(160);

    model.component("comp1").physics("emw").feature("lport1").selection().named("sel1");

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u96c6\u603b\u7aef\u53e3 2");
    model.component("comp1").selection("sel2").set(75);

    model.component("comp1").physics("emw").feature("lport2").selection().named("sel2");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u96c6\u603b\u7aef\u53e3 3");
    model.component("comp1").selection("sel3").set(242);

    model.component("comp1").physics("emw").feature("lport3").selection().named("sel3");

    model.study().create("std2");
    model.study("std2").create("frawe", "FrequencyAdaptive");
    model.study("std2").feature("frawe").set("plotgroup", "Default");
    model.study("std2").feature("frawe").set("solnum", "auto");
    model.study("std2").feature("frawe").set("notsolnum", "auto");
    model.study("std2").feature("frawe").set("outputmap", new String[]{});
    model.study("std2").feature("frawe").setSolveFor("/physics/emw", true);
    model.study("std2").feature("frawe").set("plist", "range(2[GHz],10[MHz],4[GHz])");
    model.study("std2").feature("frawe").set("awefunctype", "usercontrolled");
    model.study("std2").feature("frawe").setIndex("awefunc", "abs(comp1.emw.S11)", 0);
    model.study("std2").feature("frawe").set("rtol", 0.1);
    model.study("std2").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "emw", "sel1;sel2;sel3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (emw) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 201, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg5").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg6").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"S11", "S21", "S31"});
    model.result("pg6").label("S \u53c2\u6570 (emw) 1");
    model.result("pg6").feature("glob1").set("titletype", "none");
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg6").feature("glob1").set("xdataexpr", "freq");
    model.result("pg6").feature("glob1").set("xdataunit", "GHz");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg7", "SmithGroup");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("rgr1", "ReflectionGraph");
    model.result("pg7").feature("rgr1").set("unit", new String[]{""});
    model.result("pg7").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg7").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg7").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg7").feature("rgr1").set("titletype", "manual");
    model.result("pg7").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg7").feature("rgr1").set("linemarker", "point");
    model.result("pg7").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("rgr1").create("col1", "Color");
    model.result("pg7").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg7").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().remove("mslc1");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(75, 160, 242);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("descr", "S11 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "S21 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "S31 \u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf", 2);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("descr", "S11 \u5e38\u89c4\u626b\u63cf", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "S21 \u5e38\u89c4\u626b\u63cf", 1);
    model.result("pg6").feature("glob2").setIndex("descr", "S31 \u5e38\u89c4\u626b\u63cf", 2);
    model.result("pg6").feature("glob2").set("data", "dset1");
    model.result("pg6").feature("glob2").set("linestyle", "dotted");
    model.result("pg6").feature("glob2").set("linemarker", "cycle");
    model.result("pg6").run();
    model.result("pg7").run();

    model.title("\u91c7\u7528 SMA \u8fde\u63a5\u5668\u8fde\u63a5\u7684 Wilkinson \u529f\u5206\u5668");

    model
         .description("\u7535\u963b\u529f\u5206\u5668\u548c T \u578b\u7ed3\u529f\u5206\u5668\u662f\u4e24\u79cd\u5e38\u7528\u7684\u4e09\u7aef\u53e3\u529f\u5206\u5668\u3002\u8fd9\u7c7b\u529f\u5206\u5668\u5728\u6240\u6709\u7aef\u53e3\u4e2d\uff0c\u8981\u4e48\u4ea7\u751f\u635f\u8017\uff0c\u8981\u4e48\u4e0e\u7cfb\u7edf\u53c2\u8003\u963b\u6297\u4e0d\u5339\u914d\u3002\u6b64\u5916\uff0c\u5728\u4e24\u4e2a\u8026\u5408\u7aef\u53e3\u4e4b\u95f4\uff0c\u5e76\u4e0d\u4fdd\u8bc1\u7edd\u7f18\u3002Wilkinson \u529f\u5206\u5668\u540c\u65f6\u5177\u6709\u4ee5\u4e0a\u4e24\u79cd\u529f\u5206\u5668\u7684\u4f18\u70b9\uff0c\u4f46\u4e0d\u5b58\u5728\u4e0a\u8ff0\u95ee\u9898\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u8fd9\u79cd\u5668\u4ef6\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("wilkinson_power_divider.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
