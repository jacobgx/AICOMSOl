/*
 * dielectrophoretic_separation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:53 by COMSOL 6.3.0.290. */
public class dielectrophoretic_separation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.component("comp1").geom("geom1").insertFile("dielectrophoretic_separation_geom_sequence.mph", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "100[kHz]", "\u7535\u573a\u9891\u7387");
    model.param().set("sigma_f", "55[mS/m]", "\u6d41\u4f53\u4ecb\u8d28\u7535\u5bfc\u7387");
    model.param().set("epsilon_f", "80", "\u6d41\u4f53\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("rho_f", "1000[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu_f", "1e-3[Pa*s]", "\u6d41\u4f53\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("rho_p", "1050[kg/m^3]", "\u9897\u7c92\u5bc6\u5ea6\uff08RBC \u548c\u8840\u5c0f\u677f\uff09");
    model.param().set("dp1", "1.8[um]", "\u7c92\u5f84\uff1a\u8840\u5c0f\u677f");
    model.param().set("dp2", "5[um]", "\u7c92\u5f84\uff1aRBC");
    model.param().set("sigma_p1", "0.25[S/m]", "\u9897\u7c92\u7535\u5bfc\u7387\uff1a\u8840\u5c0f\u677f");
    model.param().set("sigma_p2", "0.31[S/m]", "\u9897\u7c92\u7535\u5bfc\u7387\uff1aRBC");
    model.param()
         .set("epsilon_p1", "50", "\u9897\u7c92\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff1a\u8840\u5c0f\u677f");
    model.param().set("epsilon_p2", "59", "\u9897\u7c92\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff1aRBC");
    model.param().set("sigma_s1", "1e-6[S/m]", "\u58f3\u7535\u5bfc\u7387\uff1a\u8840\u5c0f\u677f");
    model.param().set("sigma_s2", "1e-6[S/m]", "\u58f3\u7535\u5bfc\u7387\uff1aRBC");
    model.param().set("epsilon_s1", "6", "\u58f3\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff1a\u8840\u5c0f\u677f");
    model.param().set("epsilon_s2", "4.44", "\u58f3\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff1aRBC");
    model.param().set("th_s1", "8[nm]", "\u58f3\u539a\u5ea6\uff1a\u8840\u5c0f\u677f");
    model.param().set("th_s2", "9[nm]", "\u58f3\u539a\u5ea6\uff1aRBC");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").set("V0", 5);
    model.component("comp1").physics("ec").feature("pot1").selection().set(8, 17, 26, 34);
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot2").set("V0", -5);
    model.component("comp1").physics("ec").feature("pot2").selection().set(13, 21, 30);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(3);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "134[um/s]");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(1);
    model.component("comp1").physics("spf").feature("inl2").set("U0in", "853[um/s]");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(40, 41);
    model.component("comp1").physics("fpt").prop("Formulation")
         .setIndex("Formulation", "NewtonianIgnoreInertialTerms", 0);
    model.component("comp1").physics("fpt").feature("pp1").label("\u8840\u5c0f\u677f");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "rho_p");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "dp1");
    model.component("comp1").physics("fpt").create("pp2", "ParticlePropertiesOther", -1);
    model.component("comp1").physics("fpt").feature("pp2").label("\u7ea2\u7ec6\u80de");
    model.component("comp1").physics("fpt").feature("pp2").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp2").set("rhop", "rho_p");
    model.component("comp1").physics("fpt").feature("pp2").set("dp", "dp2");
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 1);
    model.component("comp1").physics("fpt").feature("inl1").label("\u5165\u53e3\uff0c\u8840\u5c0f\u677f");
    model.component("comp1").physics("fpt").feature("inl1").selection().set(3);
    model.component("comp1").physics("fpt").feature("inl1").setIndex("rt", "range(0,0.01,3)", 0);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "RandomPosition");
    model.component("comp1").physics("fpt").feature().duplicate("inl2", "inl1");
    model.component("comp1").physics("fpt").feature("inl2").label("\u5165\u53e3\uff0c\u7ea2\u7ec6\u80de");
    model.component("comp1").physics("fpt").feature("inl2").set("ReleasedParticleProperties", "pp2");
    model.component("comp1").physics("fpt").create("out1", "Outlet", 1);
    model.component("comp1").physics("fpt").feature("out1").selection().set(40, 41);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeVirtualMassAndPressureGradientForces", true);
    model.component("comp1").physics("fpt").create("deff1", "DielectrophoreticForce", 2);
    model.component("comp1").physics("fpt").feature("deff1")
         .label("\u4ecb\u7535\u6cf3\u529b\uff0c\u8840\u5c0f\u677f");
    model.component("comp1").physics("fpt").feature("deff1").selection().all();
    model.component("comp1").physics("fpt").feature("deff1").set("E_src", "root.comp1.ec.Ex");
    model.component("comp1").physics("fpt").feature("deff1").set("UsePPR", true);
    model.component("comp1").physics("fpt").feature("deff1").set("ParticlesToAffect", "SingleSpecies");
    model.component("comp1").physics("fpt").feature("deff1").create("shl1", "Shell", 2);
    model.component("comp1").physics("fpt").feature("deff1").feature("shl1").set("ts", "th_s1");
    model.component("comp1").physics("fpt").feature("deff1").feature("shl1").set("ers", "epsilon_s1");
    model.component("comp1").physics("fpt").feature("deff1").feature("shl1").set("sigmas", "sigma_s1");
    model.component("comp1").physics("fpt").feature().duplicate("deff2", "deff1");
    model.component("comp1").physics("fpt").feature("deff2")
         .label("\u4ecb\u7535\u6cf3\u529b\uff0c\u7ea2\u7ec6\u80de");
    model.component("comp1").physics("fpt").feature("deff2").set("AffectedParticleProperties", "pp2");
    model.component("comp1").physics("fpt").feature("deff2").feature("shl1").set("ts", "th_s2");
    model.component("comp1").physics("fpt").feature("deff2").feature("shl1").set("ers", "epsilon_s2");
    model.component("comp1").physics("fpt").feature("deff2").feature("shl1").set("sigmas", "sigma_s2");
    model.component("comp1").physics("fpt").feature("pp1").set("erp_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("erp", "epsilon_p1");
    model.component("comp1").physics("fpt").feature("pp1").set("sigmap_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("sigmap", "sigma_p1");
    model.component("comp1").physics("fpt").feature("pp2").set("erp_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp2").set("erp", "epsilon_p2");
    model.component("comp1").physics("fpt").feature("pp2").set("sigmap_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp2").set("sigmap", "sigma_p2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epsilon_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fpt", false);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ec.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "bottom");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ec", false);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std2").label("\u7814\u7a76 2\uff0c\u65e0\u4ecb\u7535\u6cf3\u529b");
    model.study("std2").feature("time").set("tlist", "range(0,0.05,3)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"fpt/deff1", "fpt/deff2"});
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg5").create("traj1", "ParticleTrajectories");
    model.result("pg5").feature("traj1").set("pointtype", "point");
    model.result("pg5").feature("traj1").set("linetype", "none");
    model.result("pg5").feature("traj1").create("col1", "Color");
    model.result("pg5").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);
    model.result("pg5").run();
    model.result("pg5").feature("traj1").set("pointradiusexpr", "if(fpt.sidx==2,dp2/2,dp1)");
    model.result("pg5").run();
    model.result("pg5").feature("traj1").feature("col1").set("expr", "fpt.dp");
    model.result("pg5").feature("traj1").feature("col1").set("colortable", "WaveLight");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "spf.U");
    model.result("pg5").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg5").feature("surf1").set("unit", "mm/s");
    model.result("pg5").feature("surf1").set("colortable", "GrayScale");
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormin", -1);
    model.result("pg5").feature("surf1").set("rangecolormax", 1.5);

    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ec", false);
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/fpt", true);
    model.study("std3").label("\u7814\u7a76 3\uff0c\u4ecb\u7535\u6cf3\u529b");
    model.study("std3").feature("time").set("tlist", "range(0,0.05,3)");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std1");
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol4").runFromTo("st1", "v1");

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol4");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_fpt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "fpt");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "part2");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").label("\u7c92\u5b50\u8f68\u8ff9 (fpt) 1");
    model.result("pg6").create("traj1", "ParticleTrajectories");
    model.result("pg6").feature("traj1").set("pointtype", "point");
    model.result("pg6").feature("traj1").set("linetype", "none");
    model.result("pg6").feature("traj1").create("col1", "Color");
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg6").run();
    model.result("pg6").set("showlegends", false);
    model.result("pg6").run();
    model.result("pg6").feature("traj1").set("pointradiusexpr", "if(fpt.dp==dp2,dp2/2,dp1)");
    model.result("pg6").run();
    model.result("pg6").feature("traj1").feature("col1").set("expr", "fpt.dp");
    model.result("pg6").feature("traj1").feature("col1").set("colortable", "WaveLight");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("unit", "mm/s");
    model.result("pg6").feature("surf1").set("colortable", "GrayScale");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", -1);
    model.result("pg6").feature("surf1").set("rangecolormax", 1.5);
    model.result("pg6").run();
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg6").feature("str1").set("descr", "\u7535\u573a");
    model.result("pg6").feature("str1").set("startmethod", "coord");
    model.result("pg6").feature("str1")
         .set("xcoord", "range(102.5,5,137.5) range(262.5,5,297.5) range(422.5,5,457.5)");
    model.result("pg6").feature("str1").set("ycoord", 60);
    model.result("pg6").feature("str1").set("color", "yellow");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowcountactive", true);
    model.result("pg6").feature("str1").set("arrowcount", 100);
    model.result("pg6").feature().move("str1", 0);

    model.study("std3").feature("time").set("plot", true);
    model.study("std3").feature("time").set("plotgroup", "pg6");
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg6").run();
    model.result("pg6").run();

    model.title("\u4f7f\u7528\u4ecb\u7535\u6cf3\u4ece\u7ea2\u7ec6\u80de\u4e2d\u5206\u79bb\u8840\u5c0f\u677f");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5728\u5fae\u6d41\u9053\u4e2d\u4f7f\u7528\u4ecb\u7535\u6cf3 (DEP) \u4ece\u7ea2\u7ec6\u80de (RBC) \u4e2d\u8fde\u7eed\u5206\u79bb\u8840\u5c0f\u677f (PLT)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("dielectrophoretic_separation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
