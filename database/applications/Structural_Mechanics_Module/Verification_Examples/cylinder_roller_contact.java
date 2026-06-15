/*
 * cylinder_roller_contact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:23 by COMSOL 6.3.0.290. */
public class cylinder_roller_contact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("E1", "70[GPa]", "\u94dd\u5757\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param().set("E2", "210[GPa]", "\u5706\u67f1\u4f53\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu0", "0.3", "\u6cca\u677e\u6bd4");
    model.param().set("Fn", "35[kN]", "\u5916\u90e8\u8f7d\u8377");
    model.param().set("E_star", "2*E1*E2/((E1+E2)*(1-nu0^2))", "\u7ec4\u5408\u6768\u6c0f\u6a21\u91cf");
    model.param().set("R", "50[mm]", "\u7ec4\u5408\u534a\u5f84");
    model.param().set("d", "200[mm]", "\u94dd\u5757\u5bbd\u5ea6");
    model.param().set("th", "1[mm]", "\u539a\u5ea6");
    model.param().set("lc", "10[mm]", "\u4f30\u8ba1\u7684\u63a5\u89e6\u957f\u5ea6");
    model.param().set("a", "sqrt(8*Fn*R/(pi*E_star*th))", "\u89e3\u6790\u63a5\u89e6\u957f\u5ea6");
    model.param().set("pmax", "sqrt(Fn*E_star/(2*pi*R*th))", "\u6700\u5927\u63a5\u89e6\u538b\u529b");
    model.param().set("dist", "1[mm]", "\u96f6\u4ef6\u4e4b\u95f4\u7684\u521d\u59cb\u8ddd\u79bb");
    model.param().set("k", "0", "\u5f39\u7c27\u7cfb\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("p_analytical", "pmax*sqrt(1-(x/a)^2)");
    model.component("comp1").variable("var1").descr("p_analytical", "\u63a5\u89e6\u538b\u529b\u89e3\u6790\u503c");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "R+dist"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d/2", "d"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-d"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d/2", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "R/2");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new String[]{"0", "-R/2"});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "dist", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 10);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"0", "R+dist"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("c1", "r1", "rot1", "sq1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 1, 2, 3);
    model.component("comp1").geom("geom1").run("mcd1");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(3);
    model.component("comp1").pair("p1").destination().set(7);

    model.component("comp1").physics("solid").prop("d").set("d", "th");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 4, 5);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("solid").feature("pl1").selection().set(5);
    model.component("comp1").physics("solid").feature("pl1").set("forcePoint", new String[]{"0", "-Fn/2", "0"});
    model.component("comp1").physics("solid").create("spf1", "SpringFoundation0", 0);
    model.component("comp1").physics("solid").feature("spf1").selection().set(5);
    model.component("comp1").physics("solid").feature("spf1")
         .set("kSpring", new String[]{"k", "0", "0", "0", "k", "0", "0", "0", "k"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"E2"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "linear");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 10, 11);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "E1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "E1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "k", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "Fn/dist/5 0", 0);
    model.study("std1").label("\u7814\u7a76 1\uff1a\u7f5a\u51fd\u6570");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 2500);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u63a5\u89e6\u538b\u529b");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(7);
    model.result("pg2").feature("lngr1").set("expr", "p_analytical");
    model.result("pg2").feature("lngr1").set("descr", "\u63a5\u89e6\u538b\u529b\u89e3\u6790\u503c");
    model.result("pg2").feature("lngr1").set("unit", "MPa");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg2").feature("lngr1").set("linestyle", "dashed");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u89e3\u6790\u503c", 0);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "gpeval(4,solid.Tn)");
    model.result("pg2").feature("lngr2").set("linestyle", "solid");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u8ba1\u7b97\u503c\uff08\u7f5a\u51fd\u6570\uff09", 0);
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u8ddd\u4e2d\u5fc3\u7684\u8ddd\u79bb (mm)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u63a5\u89e6\u538b\u529b (MPa)");
    model.result("pg2").run();

    model.component("comp1").physics("solid").create("cnt1", "SolidContact", 1);
    model.component("comp1").physics("solid").feature("cnt1").set("pairs", new String[]{"p1"});
    model.component("comp1").physics("solid").feature("cnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature().duplicate("cnt2", "cnt1");
    model.component("comp1").physics("solid").feature("cnt2").set("SolutionMethod", "coupled");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/cnt2"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "E1", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "E1", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std2").feature("stat").setIndex("pname", "k", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "Fn/dist/5 0", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u589e\u5e7f\u62c9\u683c\u6717\u65e5\uff0c\u5206\u79bb");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_solid_Tn_p1").set("scaleval", "1e9");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "E1", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "E1", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std3").feature("stat").setIndex("pname", "k", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "Fn/dist/5 0", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u589e\u5e7f\u62c9\u683c\u6717\u65e5\uff0c\u5168\u8026\u5408");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("v1").feature("comp1_solid_Tn_p1").set("scaleval", "1e9");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr3", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("data", "dset2");
    model.result("pg2").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr3")
         .setIndex("legends", "\u8ba1\u7b97\u503c\uff08\u589e\u5e7f\u62c9\u683c\u6717\u65e5\uff0cseg.\uff09", 0);
    model.result("pg2").feature().duplicate("lngr4", "lngr3");
    model.result("pg2").run();
    model.result("pg2").feature("lngr4").set("data", "dset3");
    model.result("pg2").feature("lngr4")
         .setIndex("legends", "\u8ba1\u7b97\u503c\uff08\u589e\u5e7f\u62c9\u683c\u6717\u65e5\uff0ccpl.\uff09", 0);
    model.result("pg2").run();

    model.component("comp1").physics("solid").create("cnt3", "SolidContact", 1);
    model.component("comp1").physics("solid").feature("cnt3").set("pairs", new String[]{"p1"});
    model.component("comp1").physics("solid").feature("cnt3").set("ContactMethodCtrl", "Nitsche");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "E1", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std4").feature("stat").setIndex("pname", "E1", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std4").feature("stat").setIndex("pname", "k", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "Fn/dist/5 0", 0);
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u7814\u7a76 4\uff1aNitsche");
    model.study("std4").showAutoSequences("all");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr5", "lngr4");
    model.result("pg2").run();
    model.result("pg2").feature("lngr5").set("data", "dset4");
    model.result("pg2").feature("lngr5").set("expr", "gpeval(8,solid.Tn)");
    model.result("pg2").feature("lngr5").setIndex("legends", "\u8ba1\u7b97\u503c\uff08Nitsche\uff09", 0);
    model.result("pg2").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/cnt1", "solid/cnt2", "solid/cnt3"});
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/cnt2", "solid/cnt3"});
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/cnt3"});

    model.result("pg1").run();

    model.title("\u7b52\u8f8a\u63a5\u89e6");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u94a2\u7b52\u538b\u5165\u94dd\u5757\u7684\u60c5\u51b5\uff0c\u5176\u4e2d\u7b52\u4f53\u9876\u90e8\u53d7\u5230\u5747\u5300\u8f7d\u8377\u3002\u672c\u4f8b\u4f7f\u7528\u7f5a\u51fd\u6570\u6cd5\u3001\u589e\u5e7f\u62c9\u683c\u6717\u65e5\u65b9\u6cd5\u548c Nitsche \u65b9\u6cd5\u6765\u8ba1\u7b97\u63a5\u89e6\u538b\u529b\u5206\u5e03\u548c\u63a5\u89e6\u957f\u5ea6\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("cylinder_roller_contact.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
