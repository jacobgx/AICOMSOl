/*
 * twophase_flow_fsi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class twophase_flow_fsi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("pf", "PhaseField", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics("shell").field("displacement").field("u_shell");
    model.component("comp1").physics("shell").field("displacement")
         .component(new String[]{"u_shell", "v_shell", "w_shell"});
    model.component("comp1").physics("shell").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowPhaseField", 3);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "pf");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 2);
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "shell");
    model.component("comp1").multiphysics("fsi1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/pf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/shell", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/fsi1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/pf", true);
    model.study("std1").feature("time").setSolveFor("/physics/shell", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/fsi1", true);

    model.param().set("Hb", "10[mm]");
    model.param().descr("Hb", "\u76d2\u5b50\u9ad8\u5ea6");
    model.param().set("Wb", "5[mm]");
    model.param().descr("Wb", "\u76d2\u5b50\u5bbd\u5ea6");
    model.param().set("Lb", "30[mm]");
    model.param().descr("Lb", "\u76d2\u5b50\u957f\u5ea6");
    model.param().set("Xo", "15[mm]");
    model.param().descr("Xo", "\u969c\u788d\u7269\u7684\u4f4d\u7f6e");
    model.param().set("do", "0.3[mm]");
    model.param().descr("do", "\u969c\u788d\u7269\u539a\u5ea6");
    model.param().set("Ho", "9[mm]");
    model.param().descr("Ho", "\u969c\u788d\u7269\u9ad8\u5ea6");
    model.param().set("Wo", "4[mm]");
    model.param().descr("Wo", "\u969c\u788d\u7269\u5bbd\u5ea6");
    model.param().set("Xi", "10[mm]");
    model.param().descr("Xi", "\u754c\u9762\u7684\u521d\u59cb\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lb", "Wb", "Hb"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layername", "\u5c42 1", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Xi", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layername", "\u5c42 2", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Xi", 1);
    model.component("comp1").geom("geom1").feature("blk1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u969c\u788d\u7269");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "Xo");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"Wo", "Ho"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "Hb-Ho"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "Wo/2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 7, 13);
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1, 3, 4, 5, 8, 9, 10, 14, 15, 16, 17);
    model.component("comp1").selection("sel2").label("\u58c1");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("rref", new String[]{"0", "0", "Hb"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "spf.rho*g_const*(Hb-z)");
    model.component("comp1").physics("spf").feature("init1").set("CompensateForHydrostaticPressure", false);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_wp1_bnd");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(8, 10);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "ZeroFixedWall");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(20);
    model.component("comp1").physics("spf").feature("prpc1").set("CompensateForHydrostaticPressure", false);
    model.component("comp1").physics("pf").feature("pfm1").set("chi", 5);
    model.component("comp1").physics("pf").feature("initfluid2").selection().set(2, 3);
    model.component("comp1").physics("pf").create("sym1", "SymmetryFluid", 2);
    model.component("comp1").physics("pf").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("pf").create("iww1", "InteriorWettedWall", 2);
    model.component("comp1").physics("pf").feature("iww1").selection().named("geom1_wp1_bnd");
    model.component("comp1").physics("shell").selection().named("geom1_wp1_bnd");
    model.component("comp1").physics("shell").feature("to1").set("d", "do");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(19);
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(17);

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
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("colornoisescale", 0.1);
    model.component("comp1").material("mat2").set("colornoisefrequency", 300);
    model.component("comp1").material("mat2").set("noisecolorblend", 0.4);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5c3c\u9f99");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("geom1_wp1_bnd");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"2[MPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1000"});

    model.component("comp1").multiphysics("tpf1").set("Fluid1", "mat2");
    model.component("comp1").multiphysics("tpf1").set("Fluid2", "mat1");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("phasei").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", false);

    model.component("comp1").common("free1").selection().set(2);
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(7, 8, 10);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u6240\u6709\u58c1");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("\u5185\u58c1");
    model.result().dataset("surf3").set("data", "none");
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(1, 3, 4, 5, 8, 9, 10, 14, 15, 16, 17);
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(1, 3, 4, 5, 8, 9, 10, 11, 14, 15, 16, 17);
    model.result().dataset("surf3").set("data", "dset1");
    model.result().dataset("surf3").selection().geom("geom1", 2);
    model.result().dataset("surf3").selection().set(11);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("data", "surf2");
    model.result("pg2").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "surf2");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature().create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("data", "surf3");
    model.result("pg2").feature("slit1").setIndex("looplevel", 1, 0);
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("upexpr", "up(p)");
    model.result("pg2").feature("slit1").set("downexpr", "down(p)");
    model.result("pg2").feature("slit1").set("titletype", "none");
    model.result("pg2").feature("slit1").set("smooth", "internal");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("data", "surf3");
    model.result("pg2").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (pf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "pf.Vf1");
    model.result("pg3").feature("slc1").set("smooth", "internal");
    model.result("pg3").feature("slc1").set("data", "parent");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", "pf.Vf1");
    model.result("pg3").feature("iso1").set("levelmethod", "levels");
    model.result("pg3").feature("iso1").set("levels", "0.5");
    model.result("pg3").feature("iso1").set("coloring", "uniform");
    model.result("pg3").feature("iso1").set("colorlegend", false);
    model.result("pg3").feature("iso1").set("color", "gray");
    model.result("pg3").feature("iso1").set("smooth", "none");
    model.result("pg3").feature("iso1").set("data", "parent");
    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1shellshl");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").label("\u5e94\u529b (shell)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u52a8\u7f51\u683c");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("meshdomain", "volume");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg5").feature("mesh1").feature("sel1").selection().set(2);
    model.result("pg5").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg5").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg5").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature().remove("slc1");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").feature("mtrl1").set("material", "mat2");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("iso1").feature("tran1").set("transparency", 0.15);
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "pf.Vf1");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("rangecoloractive", true);
    model.result("pg3").feature("surf2").set("rangecolormin", 0.5);
    model.result("pg3").feature("surf2").set("rangecolormax", 0.5);
    model.result("pg3").feature("surf2").set("rangedataactive", true);
    model.result("pg3").feature("surf2").set("rangedatamin", 0.5);
    model.result("pg3").feature("surf2").set("rangedatamax", 1);
    model.result("pg3").feature("surf2").set("smooth", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "iso1");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().set(2, 7, 13);
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("mtrl1").set("material", "mat2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf3", "Surface");
    model.result("pg3").feature("surf3").set("expr", "shell.disp");
    model.result("pg3").feature("surf3").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature("surf3").set("coloring", "gradient");
    model.result("pg3").feature("surf3").set("topcolor", "red");
    model.result("pg3").feature("surf3").set("bottomcolor", "gray");
    model.result("pg3").feature("surf3").set("rangecoloractive", true);
    model.result("pg3").feature("surf3").set("rangecolormax", 3);

    model.study("std1").feature("time").set("tlist", "range(0,5e-3,0.5)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.01);
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg3");

    model.sol("sol1").feature("v2").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_p").set("scaleval", 100);
    model.sol("sol1").feature("v2").feature("comp1_spatial_disp").set("scaleval", "3e-3");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", 1);
    model.sol("sol1").feature("v2").feature("comp1_u_shell").set("scaleval", "3e-3");
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subjtech", "onevery");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").run();
    model.result("pg2").feature("slit1").set("solutionparams", "parent");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 17, 0);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 17, 0);
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 101, 0);
    model.result("pg6").label("\u9762\u8f7d\u8377 (shell)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg6").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").active(false);
    model.result("pg6").feature("surf1").set("inheritcolor", false);
    model.result("pg6").feature("surf1").set("inheritrange", false);
    model.result("pg6").feature("surf1").set("inherittransparency", false);
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(11);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"fsi1.F_Atx", "fsi1.F_Aty", "fsi1.F_Atz"});
    model.result("pg6").feature("arws1").set("placement", "gausspoints");
    model.result("pg6").feature("arws1").set("arrowbase", "tail");
    model.result("pg6").feature("arws1").label("\u6d41-\u56fa\u8026\u5408 1 (\u5207\u5411\u8f7d\u8377)");
    model.result("pg6").feature("arws1").set("inheritplot", "none");
    model.result("pg6").feature("arws1").create("col", "Color");
    model.result("pg6").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arws1").set("color", "red");
    model.result("pg6").feature().move("surf1", 1);
    model.result("pg6").create("arws2", "ArrowSurface");
    model.result("pg6").feature("arws2").set("expr", new String[]{"fsi1.F_Anx", "fsi1.F_Any", "fsi1.F_Anz"});
    model.result("pg6").feature("arws2").set("placement", "gausspoints");
    model.result("pg6").feature("arws2").set("arrowbase", "tail");
    model.result("pg6").feature("arws2").label("\u6d41-\u56fa\u8026\u5408 1 (\u6cd5\u5411\u8f7d\u8377)");
    model.result("pg6").feature("arws2").set("inheritplot", "arws1");
    model.result("pg6").feature("arws2").create("col", "Color");
    model.result("pg6").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arws2").set("color", "red");
    model.result("pg6").feature().move("surf1", 2);
    model.result("pg6").label("\u9762\u8f7d\u8377 (shell)");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 17, 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u969c\u788d\u7269\u4f4d\u79fb");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(9);
    model.result("pg7").feature("ptgr1").set("expr", "u_shell");
    model.result("pg7").feature("ptgr1").set("linewidth", 2);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "X \u5206\u91cf", 0);
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("expr", "w_shell");
    model.result("pg7").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg7").feature("ptgr2").setIndex("legends", "Z \u5206\u91cf", 0);
    model.result("pg7").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").selection().set(1, 2, 3);
    model.result().numerical("int1").setIndex("expr", "phipf", 0);
    model.result().numerical("int1").setIndex("unit", "l", 0);
    model.result().numerical("int1").setIndex("descr", "\u76f8\u573a\u53d8\u91cf", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("source", "table");
    model.result("pg8").feature("tblp1").set("table", "tbl1");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("\u6c34\u4f53\u79ef");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("linewidth", 2);
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u6c34\u4f53\u79ef (l)");
    model.result("pg8").run();
    model.result("pg3").run();

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg3").set("titletype", "none");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("showlegends", false);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 8, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 17, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 28, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 34, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 48, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 54, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").run();

    model.component("comp1").view("view1").set("showaxisorientation", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg3").set("titletype", "auto");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 17, 0);
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u4e24\u76f8\u6d41-\u56fa\u8026\u5408");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5728 COMSOL Multiphysics \u4e2d\u6a21\u62df\u6d89\u53ca\u4e24\u79cd\u6db2\u76f8\u7684\u6d41-\u56fa\u8026\u5408\u7684\u6280\u672f\uff0c\u9610\u8ff0\u4e86\u5982\u4f55\u4f7f\u7528\u4efb\u610f\u62c9\u683c\u6717\u65e5-\u6b27\u62c9 (ALE) \u6280\u672f\u548c\u9884\u5b9a\u4e49\u7684\u201c\u4e24\u76f8\u6d41\uff0c\u76f8\u573a\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u6765\u6a21\u62df\u8f83\u91cd\u7684\u6d41\u4f53\u5f15\u8d77\u969c\u788d\u7269\u8fd0\u52a8\u7684\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("twophase_flow_fsi.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
