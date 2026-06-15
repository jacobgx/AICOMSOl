/*
 * annular_ultraviolet_reactor_particle.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:25 by COMSOL 6.3.0.290. */
public class annular_ultraviolet_reactor_particle {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Ultraviolet_Sterilization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");
    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std1").feature("rtrac").setSolveFor("/physics/spf", true);
    model.study("std1").feature("rtrac").setSolveFor("/physics/fpt", true);

    model.param().set("r_lamp", "1[cm]");
    model.param().descr("r_lamp", "\u706f\u534a\u5f84");
    model.param().set("r_reac", "5[cm]");
    model.param().descr("r_reac", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L_reac", "100[cm]");
    model.param().descr("L_reac", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("L_lamp", "80[cm]");
    model.param().descr("L_lamp", "\u706f\u957f\u5ea6");
    model.param().set("d_lamp", "L_reac-L_lamp");
    model.param().descr("d_lamp", "\u706f\u4f4d\u79fb");
    model.param().set("mid_lamp", "d_lamp+L_lamp/2");
    model.param().descr("mid_lamp", "\u706f\u4e2d\u9762\u4f4d\u7f6e");
    model.param().set("P", "40[W]");
    model.param().descr("P", "\u603b\u6e90\u529f\u7387");
    model.param().set("r_inl", "3[cm]");
    model.param().descr("r_inl", "\u5165\u53e3\u534a\u5f84");
    model.param().set("L_inl", "30[cm]");
    model.param().descr("L_inl", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("z_inl", "5[cm]");
    model.param().descr("z_inl", "\u5165\u53e3 z \u5750\u6807");
    model.param().set("k_inact", "0.1[cm^2/mJ]");
    model.param().descr("k_inact", "\u5931\u6d3b\u901f\u7387\u5e38\u6570");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u53cd\u5e94\u5668");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_reac");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_reac");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u5165\u53e3\u7ba1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_inl");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_inl+r_reac");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "z_inl"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").label("\u51fa\u53e3\u7ba1");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r_inl");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "L_inl+r_reac");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "L_reac-z_inl"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2", "cyl3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").label("\u706f");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "r_lamp");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "L_lamp");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"0", "0", "d_lamp"});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl4");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("OnlyStoreAccumulatedVariables")
         .setIndex("OnlyStoreAccumulatedVariables", 1, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputePower", 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "254[nm]");
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1")
         .set("n", new double[]{1.38, 0, 0, 0, 1.38, 0, 0, 0, 1.38});
    model.component("comp1").physics("gop").feature("mp1").set("OpticalAttenuationModel", "InternalTransmittance10");
    model.component("comp1").physics("gop").feature("mp1").set("taui10_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1").set("taui10", 0.7);
    model.component("comp1").physics("gop").create("relb1", "ReleaseFromBoundary", 2);
    model.component("comp1").physics("gop").feature("relb1").selection().set(5, 6, 9, 10);
    model.component("comp1").physics("gop").feature("relb1").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("relb1").setIndex("Nr", 100000, 0);
    model.component("comp1").physics("gop").feature("relb1").set("RayDirectionVector", "Lambertian");
    model.component("comp1").physics("gop").feature("relb1").set("SpecifyInletTangentialNormal", true);
    model.component("comp1").physics("gop").feature("relb1").setIndex("Nw", 1, 0);
    model.component("comp1").physics("gop").feature("relb1").set("rax", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relb1").set("SamplingFromDistribution", "Random");
    model.component("comp1").physics("gop").feature("relb1").set("Psrc", "P");
    model.component("comp1").physics("gop").create("frc1", "FluenceRateCalculation", 3);
    model.component("comp1").physics("gop").feature("frc1").selection().set(1);
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().all();
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(20);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl1").set("V0fdf", "25[gal/min]");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(21);

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

    model.component("comp1").physics("fpt").prop("Formulation")
         .setIndex("Formulation", "NewtonianIgnoreInertialTerms", 0);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 3);
    model.component("comp1").physics("fpt").feature("df1").selection().set(1);
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(20);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", 1000, 0);
    model.component("comp1").physics("fpt").feature("inl1").set("dpro", "spf.U");
    model.component("comp1").physics("fpt").create("out1", "Outlet", 2);
    model.component("comp1").physics("fpt").feature("out1").selection().set(21);
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().set(21);
    model.component("comp1").physics("fpt").create("aux1", "AuxiliaryField", -1);
    model.component("comp1").physics("fpt").feature("aux1").label("\u5438\u6536\u5242\u91cf");
    model.component("comp1").physics("fpt").feature("aux1").set("fieldVariableName", "dose");
    model.component("comp1").physics("fpt").feature("aux1").set("R", "gop.frc1.E0");
    model.component("comp1").physics("fpt").feature("aux1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("fpt").feature("aux1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("fpt").feature("aux1").setIndex("CustomDependentVariableUnit", "J/m^2", 0, 0);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("rs", "exp(-k_inact*dose)");
    model.component("comp1").variable("var1").descr("rs", "\u5b58\u6d3b\u7387");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 0.2");
    model.study("std1").feature("rtrac").setSolveFor("/physics/spf", false);
    model.study("std1").feature("rtrac").setSolveFor("/physics/fpt", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u6ce8\u91cf\u7387\u5207\u9762\u56fe");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "gop.frc1.E0");
    model.result("pg1").feature("slc1").set("descr", "\u6ce8\u91cf\u7387");
    model.result("pg1").feature("slc1").set("unit", "mW/cm^2");
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 1);
    model.result("pg1").feature("slc1").set("colortable", "Magma");
    model.result("pg1").feature("slc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("slc1").set("colorcalibration", -1.5);
    model.result("pg1").feature("slc1").set("resolution", "norefine");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/gop", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/fpt", false);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", "last");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 1);
    model.result("pg2").feature("slc1").set("colortable", "Viridis");
    model.result("pg2").run();
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("xnumber", 15);
    model.result("pg2").feature("arwv1").set("ynumber", 3);
    model.result("pg2").feature("arwv1").set("znumber", 50);
    model.result("pg2").feature("arwv1").set("arrowtype", "cone");
    model.result("pg2").feature("arwv1").set("color", "black");
    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/gop", false);
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std3").feature("time").set("tlist", "range(0,0.1,10)");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.q2x", "comp1.q2y", "comp1.q2z"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg5").create("traj1", "ParticleTrajectories");
    model.result("pg5").feature("traj1").set("pointtype", "point");
    model.result("pg5").feature("traj1").set("linetype", "none");
    model.result("pg5").feature("traj1").create("col1", "Color");
    model.result("pg5").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("traj1").feature("col1").set("expr", "rs");
    model.result("pg5").feature("traj1").feature("col1").set("colortable", "Prism");
    model.result("pg5").run();
    model.result("pg5").feature("traj1").set("linetype", "line");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "part1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("hist1", "Histogram");
    model.result("pg6").feature("hist1").set("markerpos", "datapoints");
    model.result("pg6").feature("hist1").set("linewidth", "preference");
    model.result("pg6").feature("hist1").set("expr", "dose");
    model.result("pg6").feature("hist1").set("unit", "mJ/cm^2");
    model.result("pg6").feature("hist1").set("method", "limits");
    model.result("pg6").feature("hist1").set("limits", "range(0,5,120)");
    model.result("pg6").feature("hist1").set("function", "discrete");
    model.result("pg6").feature("hist1").set("normalization", "integralsum");
    model.result("pg6").feature("hist1").create("filt1", "HistogramFilter");
    model.result("pg6").run();
    model.result("pg6").feature("hist1").feature("filt1").set("expr", "fpt.pcnt1.rL");
    model.result("pg6").run();

    model.title("\u5e26\u7c92\u5b50\u8ffd\u8e2a\u7684\u73af\u5f62\u7d2b\u5916\u53cd\u5e94\u5668");

    model
         .description("\u672c\u4f8b\u7ec4\u5408\u4f7f\u7528\u5c04\u7ebf\u8ffd\u8e2a\u3001\u8ba1\u7b97\u6d41\u4f53\u529b\u5b66\u548c\u62c9\u683c\u6717\u65e5\u7c92\u5b50\u8ffd\u8e2a\u5bf9\u7b80\u5355\u7684\u7d2b\u5916\u7ebf (UV) \u6c34\u51c0\u5316\u53cd\u5e94\u5668\u8fdb\u884c\u5efa\u6a21\u3002\u9996\u5148\uff0c\u4f7f\u7528\u201c\u51e0\u4f55\u5149\u5b66\u201d\u63a5\u53e3\u6cbf\u7740\u4ece\u7d2b\u5916\u706f\u8868\u9762\u91ca\u653e\u7684\u5149\u7ebf\u7d2f\u79ef\u4f53\u79ef\u6ce8\u91cf\u7387\u3002\u7136\u540e\u6c42\u89e3\u53cd\u5e94\u5668\u4e2d\u7684\u6d41\u4f53\u901f\u5ea6\u3002\u6700\u540e\uff0c\u6cbf\u7740\u6d41\u4f53\u901f\u5ea6\u6d41\u7ebf\u8ffd\u8e2a\u7c92\u5b50\uff0c\u540c\u65f6\u4f7f\u7528\u201c\u8f85\u52a9\u56e0\u53d8\u91cf\u201d\u5bf9\u6cbf\u8fd9\u4e9b\u7c92\u5b50\u8f68\u8ff9\u7684\u6ce8\u91cf\u7387\u8fdb\u884c\u79ef\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("annular_ultraviolet_reactor_particle.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
