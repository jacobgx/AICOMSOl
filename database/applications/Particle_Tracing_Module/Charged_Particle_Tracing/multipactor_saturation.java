/*
 * multipactor_saturation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:51 by COMSOL 6.3.0.290. */
public class multipactor_saturation {

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
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");
    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .set("ParticleReleaseSpecification", "SpecifyCurrent");
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").set("RelativisticCorrection", "0");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce");
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("SpecifyForceUsing", new String[]{"ElectricField"});

    model.component("comp1").multiphysics().create("epfi1", "ElectricParticleFieldInteraction", 3);
    model.component("comp1").multiphysics("epfi1").set("ChargeDensitySource_physics", "cpt");
    model.component("comp1").multiphysics("epfi1").set("ChargeDensityDestination_physics", "es");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/es", true);
    model.study("std1").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/epfi1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "0.1[cm]", "\u8fb9\u957f");
    model.param().set("D", "0.16[cm]", "\u95f4\u9699\u539a\u5ea6");
    model.param().set("f0", "2.5[GHz]", "\u6fc0\u53d1\u9891\u7387");
    model.param().set("V0", "1078[V]", "\u5c04\u9891\u7535\u538b");
    model.param().set("B0", "360[G]", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.param().set("T0", "300[K]", "\u8868\u9762\u6e29\u5ea6");
    model.param().set("n", "5E4", "\u7535\u8377\u500d\u589e\u56e0\u5b50");

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1").set("tau", "f0*t", "\u5c04\u9891\u5468\u671f\u6570");
    model.variable("var1").set("relLeft", "mod(tau,1)<0.5", "\u5141\u8bb8\u4ece\u5de6\u4fa7\u91ca\u653e");
    model.variable("var1").set("relRight", "mod(tau,1)>0.5", "\u5141\u8bb8\u4ece\u53f3\u4fa7\u91ca\u653e");

    model.func().create("pw1", "Piecewise");
    model.func("pw1").setIndex("pieces", 0, 0, 0);
    model.func("pw1").setIndex("pieces", 300, 0, 1);
    model.func("pw1").setIndex("pieces", "2.8*((x/300)*exp(1-x/300))^0.6", 0, 2);
    model.func("pw1").setIndex("pieces", 300, 1, 0);
    model.func("pw1").setIndex("pieces", 5000, 1, 1);
    model.func("pw1").setIndex("pieces", "2.8*((x/300)*exp(1-x/300))^0.2", 1, 2);
    model.func("pw1").set("argunit", "eV");
    model.func("pw1").set("fununit", "1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"D", "L", "L"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var2");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("SEY_int", "floor(pw1(cpt.Ep))", "SEY \u6574\u6570");
    model.component("comp1").variable("var2").set("SEY_frac", "mod(pw1(cpt.Ep),1)", "SEY \u4f59\u6570");
    model.component("comp1").variable("var2")
         .set("SEY_int_left", "if(relLeft,SEY_int,0)", "\u5de6\u4fa7 SEY \u6574\u6570");
    model.component("comp1").variable("var2")
         .set("SEY_frac_left", "if(relLeft,SEY_frac,0)", "\u5de6\u4fa7 SEY \u4f59\u6570");
    model.component("comp1").variable("var2")
         .set("SEY_int_right", "if(relRight,SEY_int,0)", "\u53f3\u4fa7 SEY \u6574\u6570");
    model.component("comp1").variable("var2")
         .set("SEY_frac_right", "if(relRight,SEY_frac,0)", "\u53f3\u4fa7 SEY \u4f59\u6570");
    model.component("comp1").variable("var2")
         .set("impacts_left", "cpt.wall2.bacc1.rpb_ave", "\u5355\u4f4d\u9762\u79ef\u7684\u649e\u51fb\uff0c\u5de6\u4fa7");
    model.component("comp1").variable("var2")
         .set("impacts_right", "cpt.wall3.bacc1.rpb_ave", "\u5355\u4f4d\u9762\u79ef\u7684\u649e\u51fb\uff0c\u53f3\u4fa7");
    model.component("comp1").variable("var2")
         .set("impacts_all", "impacts_left+impacts_right", "\u5355\u4f4d\u9762\u79ef\u7684\u649e\u51fb\uff0c\u603b\u91cf");
    model.component("comp1").variable("var2")
         .set("impacts_change", "impacts_all-at(t-0.5/f0,impacts_all)", "\u6bcf\u534a\u5468\u671f\u7684\u7535\u5b50\u649e\u51fb");

    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(1);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(6);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0*sin(2*pi*f0*t)");
    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .setIndex("ParticleReleaseSpecification", "SpecifyReleaseTimes", 0);
    model.component("comp1").physics("cpt").prop("MaximumSecondary").setIndex("MaximumSecondary", 1000, 0);
    model.component("comp1").physics("cpt").prop("ReuseParticleDegreesOfFreedom")
         .setIndex("ReuseParticleDegreesOfFreedom", "AllDisappearedParticles", 0);
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").create("mf1", "MagneticForce", 3);
    model.component("comp1").physics("cpt").feature("mf1").selection().set(1);
    model.component("comp1").physics("cpt").feature("mf1").set("B", new String[]{"0", "0", "B0"});
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "range(0.01,0.02,0.99)*D", 0);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "L/2", 1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "L/2", 2);
    model.component("comp1").physics("cpt").create("wall2", "Wall", 2);
    model.component("comp1").physics("cpt").feature("wall2").selection().set(1);
    model.component("comp1").physics("cpt").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("cpt").feature("wall2").create("sem1", "SecondaryEmission", 2);
    model.component("comp1").physics("cpt").feature("wall2").feature("sem1").set("Ns", "SEY_int_left");
    model.component("comp1").physics("cpt").feature("wall2").feature("sem1").set("InitialVelocity", "Thermal");
    model.component("comp1").physics("cpt").feature("wall2").feature("sem1").set("T", "T0");
    model.component("comp1").physics("cpt").feature("wall2").feature().duplicate("sem2", "sem1");
    model.component("comp1").physics("cpt").feature("wall2").feature("sem2")
         .set("SecondaryEmissionCondition", "Probability");
    model.component("comp1").physics("cpt").feature("wall2").feature("sem2").set("gamma", "SEY_frac_left");
    model.component("comp1").physics("cpt").feature("wall2").feature("sem2").set("Ns", 1);
    model.component("comp1").physics("cpt").feature("wall2").create("bacc1", "BoundaryAccumulator", 2);
    model.component("comp1").physics("cpt").feature("wall2").feature("bacc1")
         .set("DependentVariableQuantity", "surfacechargedensity");
    model.component("comp1").physics("cpt").feature("wall2").feature("bacc1").set("R", "e_const*n");
    model.component("comp1").physics("cpt").feature().duplicate("wall3", "wall2");
    model.component("comp1").physics("cpt").feature("wall3").selection().set(6);
    model.component("comp1").physics("cpt").feature("wall3").feature("sem1").set("Ns", "SEY_int_right");
    model.component("comp1").physics("cpt").feature("wall3").feature("sem2").set("gamma", "SEY_frac_right");
    model.component("comp1").physics("cpt").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("cpt").feature("pc1").selection().set(3, 4);
    model.component("comp1").physics("cpt").create("pc2", "PeriodicCondition", 2);
    model.component("comp1").physics("cpt").feature("pc2").selection().set(2, 5);

    model.component("comp1").multiphysics("epfi1").set("n", "n");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 100);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,1/(100*f0),20/f0)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");

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
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 2001, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part1");
    model.result("pg4").setIndex("looplevel", 2001, 0);
    model.result("pg4").label("\u7d2f\u79ef\u53d8\u91cf (cpt)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "cpt.wall2.bacc1.rpb");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"impacts_change"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u6bcf\u534a\u5468\u671f\u7684\u7535\u5b50\u649e\u51fb"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"C/m^2"});
    model.result("pg5").feature("glob1").setIndex("unit", "nC/cm^2", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "tau");
    model.result("pg5").feature("glob1").set("xdatadescr", "\u5c04\u9891\u5468\u671f\u6570");
    model.result("pg5").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", "L/2");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("data", "cpl1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "epfi1.rhos");
    model.result("pg6").feature("surf1").set("descr", "\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6");
    model.result("pg6").feature("surf1").set("colortable", "GrayScale");
    model.result("pg6").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").run();
    model.result().export("anim1").set("maxframes", 200);
    model.result().export("anim1").run();

    model.title("\u500d\u589e\u7ba1\u9971\u548c");

    model
         .description("\u5f53\u7535\u5b50\u5728\u63a5\u8fd1\u771f\u7a7a\u7684\u6761\u4ef6\u4e0b\u88ab\u8154\u5185\u7684\u9ad8\u9891\u5c04\u9891\u573a\u52a0\u901f\u65f6\uff0c\u5c31\u4f1a\u53d1\u751f\u4e8c\u6b21\u7535\u5b50\u500d\u589e\u6548\u5e94\u3002\u5728\u57fa\u4e8e\u8154\u4f53\u5927\u5c0f\u7684\u67d0\u4e9b\u8c10\u632f\u9891\u7387\u9644\u8fd1\uff0c\u7535\u5b50\u53ef\u4ee5\u901a\u8fc7\u9ad8\u901f\u649e\u51fb\u8154\u58c1\u6765\u89e6\u53d1\u7ea7\u8054\u7684\u4e8c\u6b21\u7535\u5b50\u53d1\u5c04\u3002\u6700\u7ec8\uff0c\u8154\u5185\u7684\u7535\u5b50\u6570\u8fbe\u5230\u52a8\u6001\u5e73\u8861\u72b6\u6001\uff0c\u56e0\u4e3a\u76f8\u4e92\u95f4\u7684\u9759\u7535\u6392\u65a5\u5bfc\u81f4\u7535\u5b50\u5e26\u53d8\u5bbd\uff0c\u4ece\u800c\u4f7f\u6240\u6709\u58c1\u78b0\u649e\u7684\u5e73\u5747\u4e8c\u6b21\u7535\u5b50\u53d1\u5c04\u7cfb\u6570\u90fd\u63a5\u8fd1\u4e00\u3002\u968f\u4e4b\u53d1\u751f\u7684\u7a33\u6001\u884c\u4e3a\u79f0\u4e3a\u500d\u589e\u7ba1\u9971\u548c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("multipactor_saturation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
