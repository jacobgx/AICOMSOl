/*
 * childs_law_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:49 by COMSOL 6.3.0.290. */
public class childs_law_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");
    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .set("ParticleReleaseSpecification", "SpecifyCurrent");
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").set("RelativisticCorrection", "0");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce");
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("SpecifyForceUsing", new String[]{"ElectricField"});

    model.component("comp1").multiphysics().create("epfi1", "ElectricParticleFieldInteraction", 2);
    model.component("comp1").multiphysics("epfi1").set("ChargeDensitySource_physics", "cpt");
    model.component("comp1").multiphysics("epfi1").set("ChargeDensityDestination_physics", "es");

    model.study().create("std1");
    model.study("std1").create("bcpt", "BidirectionallyCoupledParticleTracing");
    model.study("std1").feature("bcpt").setSolveFor("/physics/es", true);
    model.study("std1").feature("bcpt").setSolveFor("/physics/cpt", true);
    model.study("std1").feature("bcpt").setSolveFor("/multiphysics/epfi1", true);

    model.param().set("V0", "1000[V]");
    model.param().descr("V0", "\u95f4\u9699\u4e2d\u7684\u7535\u52bf\u5dee");
    model.param().set("d", "1[cm]");
    model.param().descr("d", "\u5efa\u6a21\u57df\u957f\u5ea6");
    model.param().set("dbuf", "0.01[cm]");
    model.param().descr("dbuf", "\u7f13\u51b2\u533a\u957f\u5ea6");
    model.param().set("H", "2[cm]");
    model.param().descr("H", "\u5efa\u6a21\u57df\u9ad8\u5ea6");
    model.param().set("Depth", "1[m]");
    model.param().descr("Depth", "\u5efa\u6a21\u57df\u6df1\u5ea6");
    model.param().set("Jan", "(4*epsilon0_const/9)*sqrt(2*V0*e_const/me_const)*V0/d^2");
    model.param().descr("Jan", "\u89e3\u6790\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Ian", "Jan*H*Depth");
    model.param().descr("Ian", "\u89e3\u6790\u603b\u7535\u6d41");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d", "H"});

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Van", "V0*((x+dbuf)/(d+dbuf))^(4/3)");
    model.component("comp1").variable("var1").descr("Van", "\u89e3\u6790\u7535\u52bf\u5206\u5e03");
    model.component("comp1").variable("var1").set("rel_err", "(V-Van)/Van");
    model.component("comp1").variable("var1").descr("rel_err", "\u7535\u52bf\u5206\u5e03\u9519\u8bef");

    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Electron");
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(4);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");

    model.component("comp1").multiphysics().create("scle1", "SpaceChargeLimitedEmission", 1);
    model.component("comp1").multiphysics("scle1").selection().set(1);
    model.component("comp1").multiphysics("scle1").set("os", "dbuf");
    model.component("comp1").multiphysics("epfi1").set("UseCumulativeSpaceChargeDensity", true);
    model.component("comp1").multiphysics("epfi1").set("beta", 15);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bcpt").set("tunit", "ns");
    model.study("std1").feature("bcpt").set("tlist", "range(0,0.1,3)");
    model.study("std1").feature("bcpt").set("method", "convergence_of_global_variable");
    model.study("std1").feature("bcpt").set("expr", "scle1.rc");
    model.study("std1").feature("bcpt").set("maxiter", 35);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (es)");
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
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "es.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
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
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "1[cm]", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "1[cm]", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "1[cm]", 1, 1);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e0e Child \u5b9a\u5f8b\u6bd4\u8f83");
    model.result("pg4").set("data", "cln1");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u8ba1\u7b97\u7684\u7535\u52bf", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").set("expr", "Van");
    model.result("pg4").feature("lngr2").set("linestyle", "none");
    model.result("pg4").feature("lngr2").set("linemarker", "point");
    model.result("pg4").feature("lngr2").set("markerpos", "interp");
    model.result("pg4").feature("lngr2").set("markers", 20);
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "rel_err");
    model.result("pg5").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").set("expr", new String[]{"Ian"});
    model.result().numerical("gev1").set("descr", new String[]{"\u89e3\u6790\u603b\u7535\u6d41"});
    model.result().numerical("gev1").set("unit", new String[]{"A"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").set("expr", new String[]{"scle1.rc"});
    model.result().numerical("gev2").set("descr", new String[]{"\u91ca\u653e\u7535\u6d41\u5e45\u503c"});
    model.result().numerical("gev2").set("unit", new String[]{"A"});
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();

    model.title("Child \u5b9a\u5f8b\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5e73\u9762\u4e0a\u7535\u5b50\u7684\u7a7a\u95f4\u7535\u8377\u53d7\u9650\u53d1\u5c04\u3002\u5c06\u7535\u52bf\u5206\u5e03\u4e0e\u57fa\u4e8e Child \u5b9a\u5f8b\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\uff0c\u5e76\u4e0e\u53d1\u5c04\u9762\u4e0a\u7684\u603b\u53d1\u5c04\u7535\u6d41\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("childs_law_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
