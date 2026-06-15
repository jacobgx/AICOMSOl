/*
 * tunnel_excavation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:14 by COMSOL 6.3.0.290. */
public class tunnel_excavation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Soil");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{90, 45});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -45});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{0, -20});
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").feature("lemm1").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 3, 4, 5);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(7);
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("matchToMohrCoulomb", "inscribe");
    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("Sil", new String[]{"withsol('sol1', solid.sx)", "withsol('sol1', solid.sxy)", "withsol('sol1', solid.sxz)", "withsol('sol1', solid.sxy)", "withsol('sol1', solid.sy)", "withsol('sol1', solid.syz)", "withsol('sol1', solid.sxz)", "withsol('sol1', solid.syz)", "withsol('sol1', solid.sz)"});
    model.component("comp1").physics("solid").feature("lemm1").create("act1", "Activation", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1").set("actfac", "1e-9");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"12[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.495"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2000"});
    model.component("comp1").material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"130[kPa]"});
    model.component("comp1").material("mat1").propertyGroup("Soil").set("phis", new String[]{"30[deg]"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(8, 9);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 12);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u5f00\u6316\u524d");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/lemm1/sopl1", "solid/lemm1/iss1", "solid/lemm1/act1"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u5f00\u6316\u540e");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").label("\u5e94\u529b (solid) 1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").create("filt1", "Filter");
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "solid.isactive");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff1a\u5f00\u6316\u524d");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff1a\u5f00\u6316\u540e");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("filt1").set("nodespec", "all");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "solid.isactive");
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").run();
    model.result("pg3").label("\u5851\u6027\u533a\u57df\uff1a\u5f00\u6316\u540e");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg3").feature("surf1").set("descractive", false);
    model.result("pg3").feature("surf1").set("bandcount", 2);
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().set(1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6c34\u5e73\u4f4d\u79fb\uff1a\u5f00\u6316\u540e");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u8868\u9762\u6c34\u5e73\u4f4d\u79fb");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8ddd\u96a7\u9053\u8f74\u7684\u8ddd\u79bb (m)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6c34\u5e73\u4f4d\u79fb (mm)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(6);
    model.result("pg4").feature("lngr1").set("expr", "u");
    model.result("pg4").feature("lngr1").set("unit", "mm");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5782\u76f4\u4f4d\u79fb\uff1a\u5f00\u6316\u540e");
    model.result("pg5").set("title", "\u8868\u9762\u6c89\u964d");
    model.result("pg5").set("ylabel", "\u5782\u76f4\u4f4d\u79fb (mm)");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "v");
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u96a7\u9053\u5f00\u6316");

    model
         .description("\u96a7\u9053\u5f00\u6316\u5efa\u6a21\u662f\u5ca9\u571f\u5de5\u7a0b\u9886\u57df\u4e2d\u4e00\u4e2a\u5e38\u89c1\u7684\u9a8c\u8bc1\u95ee\u9898\u3002\u672c\u4f8b\u5305\u542b\u4e24\u4e2a\u7814\u7a76\u3002\u7b2c\u4e00\u4e2a\u7814\u7a76\u8ba1\u7b97\u96a7\u9053\u5f00\u6316\u524d\u571f\u58e4\u7684\u5e94\u529b\u72b6\u6001\uff1b\u7b2c\u4e8c\u4e2a\u7814\u7a76\u5219\u5728\u79fb\u9664\u90e8\u5206\u571f\u58e4\u540e\uff0c\u8ba1\u7b97\u4e86\u571f\u58e4\u7684\u5f39\u5851\u6027\u884c\u4e3a\uff0c\u5176\u521d\u59cb\u5e94\u529b\u53d6\u81ea\u7b2c\u4e00\u4e2a\u7814\u7a76\u3002\u672c\u4f8b\u4f7f\u7528\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u5c48\u670d\u6761\u4ef6\u5c06\u571f\u58e4\u4f5c\u4e3a\u5f39\u6027-\u7406\u60f3\u5851\u6027\u6750\u6599\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("tunnel_excavation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
