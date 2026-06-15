/*
 * pierce_electron_gun.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:51 by COMSOL 6.3.0.290. */
public class pierce_electron_gun {

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

    model.param().set("d", "5[cm]");
    model.param().set("db", "1[mm]");
    model.param().set("w1", "1[cm]");
    model.param().set("w2", "20[cm]");
    model.param().set("alpha", "22.5[deg]");
    model.param().set("V0", "1[kV]");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w1", "d-db"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "db"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parname", "theta");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", "62*pi/180");
    model.component("comp1").geom("geom1").feature("pc1")
         .set("coord", new String[]{"w1+d*sec(4*theta/3)^0.75*sin(theta)", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "d*sec(4*theta/3)^0.75*cos(theta)", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w1", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "d", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w1", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w2*tan(alpha)", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w2-d*tan(alpha)", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "w2*tan(alpha)+d", 3, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("pc1", "pol1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Electron");
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");

    model.component("comp1").multiphysics().create("scle1", "SpaceChargeLimitedEmission", 1);
    model.component("comp1").multiphysics("scle1").selection().set(2);
    model.component("comp1").multiphysics("scle1").set("os", "db");
    model.component("comp1").multiphysics("epfi1").set("UseCumulativeSpaceChargeDensity", true);
    model.component("comp1").multiphysics("epfi1").set("beta", 10);

    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").label("\u9633\u6781");
    model.component("comp1").physics("es").feature("pot1").selection().set(3, 8);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("es").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot2").label("\u805a\u7126\u7535\u6781");
    model.component("comp1").physics("es").feature("pot2").selection().set(5);
    model.component("comp1").physics("es").create("pot3", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot3").label("\u90bb\u8fd1\u865a\u9634\u6781");
    model.component("comp1").physics("es").feature("pot3").selection().set(4);
    model.component("comp1").physics("es").feature("pot3").set("V0", "intop1(scle1.V0)*(y/db)^(4/3)");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 100);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bcpt").set("tunit", "ns");
    model.study("std1").feature("bcpt").set("tlist", "range(0,1,10)");
    model.study("std1").feature("bcpt").set("iter", 20);
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
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Viridis");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("colortable", "GrayScale");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "line");
    model.result("pg3").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e26\u573a\u7ebf\u7684\u7b49\u503c\u7ebf");
    model.result("pg4").set("data", "mir1");
    model.result("pg4").set("edges", false);
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("contourtype", "filled");
    model.result("pg4").feature("con1").set("colortable", "Traffic");
    model.result("pg4").feature("con1").set("colortabletrans", "reverse");
    model.result("pg4").feature("con1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("con1").feature("filt1").set("expr", "x<5[cm]");
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("startmethod", "coord");
    model.result("pg4").feature("str1").set("xcoord", "range(-0.05,0.002,0.05)");
    model.result("pg4").feature("str1").set("ycoord", 0.045);
    model.result("pg4").feature("str1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("filt1").set("expr", "x<5[cm]");
    model.result("pg4").run();

    model.title("\u76ae\u5c14\u65af\u7535\u5b50\u67aa");

    model
         .description("\u7535\u5b50\u67aa\u5fc5\u987b\u80fd\u591f\u4ea7\u751f\u8db3\u591f\u7684\u7535\u6d41\uff0c\u5e76\u5c06\u7535\u5b50\u52a0\u901f\u5230\u6240\u9700\u7684\u901f\u5ea6\u3002\u7535\u5b50\u67aa\u51e0\u4f55\u5f62\u72b6\u7684\u7b2c\u4e00\u90e8\u5206\u63d0\u51fa\u4e86\u72ec\u7279\u7684\u8bbe\u8ba1\u6311\u6218\uff0c\u539f\u56e0\u662f\u8fd9\u91cc\u7684\u53d1\u5c04\u7535\u5b50\u901f\u5ea6\u901a\u5e38\u6700\u4f4e\uff0c\u56e0\u6b64\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6\u975e\u5e38\u9ad8\u3002\u76ae\u5c14\u65af\u7535\u5b50\u67aa\u7684\u8bbe\u8ba1\u4f7f\u7528\u5177\u6709\u7279\u5b9a\u5f62\u72b6\u7684\u7535\u6781\u6765\u62b5\u6d88\u7535\u5b50\u675f\u4e2d\u7535\u5b50\u4e4b\u95f4\u7684\u5e93\u4ed1\u65a5\u529b\uff0c\u4ece\u800c\u4f7f\u7535\u5b50\u675f\u4e2d\u7684\u7535\u5b50\u6cbf\u76f4\u7ebf\u4f20\u64ad\u3002\u672c\u4f8b\u5047\u8bbe\u9634\u6781\u53d1\u5c04\u7684\u7535\u5b50\u53d7\u7a7a\u95f4\u7535\u8377\u9650\u5236\uff1b\u5e76\u5ffd\u7565\u4e86\u7535\u5b50\u901f\u5ea6\u7684\u521d\u59cb\u70ed\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pierce_electron_gun.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
