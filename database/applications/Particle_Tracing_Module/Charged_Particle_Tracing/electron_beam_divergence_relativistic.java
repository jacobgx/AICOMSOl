/*
 * electron_beam_divergence_relativistic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:50 by COMSOL 6.3.0.290. */
public class electron_beam_divergence_relativistic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");
    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .set("ParticleReleaseSpecification", "SpecifyCurrent");
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").set("RelativisticCorrection", "1");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce");
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("SpecifyForceUsing", new String[]{"ElectricField"});
    model.component("comp1").physics("cpt").create("mf1", "MagneticForce");
    model.component("comp1").physics("cpt").feature("mf1").selection().all();

    model.component("comp1").multiphysics().create("epfi1", "ElectricParticleFieldInteraction", 3);
    model.component("comp1").multiphysics("epfi1").set("ChargeDensitySource_physics", "cpt");
    model.component("comp1").multiphysics("epfi1").set("ChargeDensityDestination_physics", "es");
    model.component("comp1").multiphysics().create("mpfi1", "MagneticParticleFieldInteraction", 3);
    model.component("comp1").multiphysics("mpfi1").set("CurrentDensitySource_physics", "cpt");
    model.component("comp1").multiphysics("mpfi1").set("CurrentDensityDestination_physics", "mf");

    model.study().create("std1");
    model.study("std1").create("bcpt", "BidirectionallyCoupledParticleTracing");
    model.study("std1").feature("bcpt").setSolveFor("/physics/es", true);
    model.study("std1").feature("bcpt").setSolveFor("/physics/mf", true);
    model.study("std1").feature("bcpt").setSolveFor("/physics/cpt", true);
    model.study("std1").feature("bcpt").setSolveFor("/multiphysics/epfi1", true);
    model.study("std1").feature("bcpt").setSolveFor("/multiphysics/mpfi1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r0beam", "0.01[m]", "\u521d\u59cb\u675f\u6d41\u534a\u5f84");
    model.param().set("r0", "0.05[m]", "\u57df\u534a\u5f84");
    model.param().set("L", "0.2[m]", "\u57df\u957f\u5ea6");
    model.param().set("Ibeam", "3[A]", "\u675f\u6d41\u5927\u5c0f");
    model.param().set("v0beam", "1e8[m/s]", "\u521d\u59cb\u675f\u6d41\u901f\u5ea6");
    model.param().set("gamma", "1/sqrt(1-(v0beam/c_const)^2)", "\u76f8\u5bf9\u8bba\u6027\u56e0\u5b50");
    model.param()
         .set("K", "e_const*Ibeam/(2*pi*epsilon0_const*me_const*(v0beam*gamma)^3)", "\u5e7f\u4e49\u5bfc\u6d41\u7cfb\u6570");
    model.param().set("hmax", "0.01", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cyl1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r0beam");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("qr", "sqrt(qx^2+qy^2)");
    model.component("comp1").variable("var1").descr("qr", "\u4e0e\u675f\u8f74\u7684\u5f84\u5411\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("qrmax", "cpt.max(qr)");
    model.component("comp1").variable("var1").descr("qrmax", "\u675f\u6d41\u534a\u5f84");
    model.component("comp1").variable("var1").set("z_avg", "cpt.ave(qz)");
    model.component("comp1").variable("var1").descr("z_avg", "z \u5750\u6807\u7684\u5e73\u5747\u503c");
    model.component("comp1").variable("var1").set("chi", "qrmax/at(0,qrmax)");
    model.component("comp1").variable("var1")
         .descr("chi", "\u675f\u6d41\u534a\u5f84\u4e0e\u675f\u8170\u534a\u5f84\u4e4b\u6bd4");

    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(1, 2, 6, 7);
    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Electron");
    model.component("comp1").physics("cpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("cpt").feature("inl1").selection().set(5);
    model.component("comp1").physics("cpt").feature("inl1").set("rc", "Ibeam");
    model.component("comp1").physics("cpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("cpt").feature("inl1").setIndex("N", 1000, 0);
    model.component("comp1").physics("cpt").feature("inl1").set("v0", new String[]{"0", "0", "v0beam"});
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);
    model.component("comp1").physics("cpt").feature("mf1").set("B_src", "root.comp1.mf.Bx");
    model.component("comp1").physics("cpt").feature("mf1").set("UsePPR", true);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bcpt").set("tlist", "range(0,1.0e-10,3e-9)");
    model.study("std1").feature("bcpt").set("usertol", true);
    model.study("std1").feature("bcpt").set("rtol", 1.0E-5);
    model.study("std1").feature("bcpt").set("method", "convergence_of_global_variable");
    model.study("std1").feature("bcpt").set("expr", "qrmax");
    model.study("std1").feature("bcpt").set("rtolterm", "1E-5");
    model.study("std1").feature("bcpt").set("rtolthresh", 0.015);
    model.study("std1").feature("bcpt").set("maxiter", 8);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("st2").set("splitcomplex", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "es.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "mf.normB");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"mf.Bx", "mf.By", "mf.Bz"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "mf.normB");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 31, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("linetype", "line");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "qr-at(0,qr)");
    model.result("pg4").feature("traj1").feature("col1").set("colortable", "Viridis");
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "bottom");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "r0beam/sqrt(2*K)*integrate(1/sqrt(log(s)),s,1+eps,chi)", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u9884\u8ba1\u7684\u7535\u5b50\u675f\u5bbd\u5ea6 z \u5750\u6807", 0);
    model.result().numerical("gev1").setIndex("expr", "z_avg", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u76f8\u5bf9\u8bba\u53d1\u6563\u7535\u5b50\u675f");

    model
         .description("\u5728\u5bf9\u5e26\u7535\u7c92\u5b50\u675f\u4ee5\u5f88\u5927\u7684\u7535\u6d41\u548c\u76f8\u5bf9\u8bba\u901f\u5ea6\u7684\u4f20\u64ad\u5efa\u6a21\u65f6\uff0c\u7a7a\u95f4\u7535\u8377\u548c\u675f\u7535\u6d41\u4ea7\u751f\u4e86\u660e\u663e\u7684\u7535\u529b\u548c\u78c1\u529b\uff0c\u5206\u522b\u4f7f\u7c92\u5b50\u675f\u5177\u6709\u53d1\u6563\u548c\u805a\u7126\u7684\u8d8b\u52bf\u3002\u4f7f\u7528\u201c\u53cc\u5411\u8026\u5408\u7c92\u5b50\u8ffd\u8e2a\u201d\u7814\u7a76\u6b65\u9aa4\u53ef\u4ee5\u8ba1\u7b97\u7c92\u5b50\u8f68\u8ff9\u4e0e\u7535\u529b/\u78c1\u529b\u4e4b\u95f4\u7684\u53cc\u5411\u5f3a\u8026\u5408\u4f5c\u7528\u3002\u672c\u4f8b\u4e2d\uff0c\u7f51\u683c\u7ec6\u5316\u7814\u7a76\u8bc1\u5b9e\uff0c\u5f97\u5230\u7684\u89e3\u4e0e\u76f8\u5bf9\u8bba\u675f\u5305\u7edc\u5f62\u72b6\u7684\u89e3\u6790\u8868\u8fbe\u5f0f\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electron_beam_divergence_relativistic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
