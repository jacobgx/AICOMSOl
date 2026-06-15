/*
 * elastic_cloaking.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:06 by COMSOL 6.3.0.290. */
public class elastic_cloaking {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("r0", "0.2[m]");
    model.param().descr("r0", "\u6597\u7bf7\u7684\u5185\u534a\u5f84");
    model.param().set("r1", "0.4[m]");
    model.param().descr("r1", "\u6597\u7bf7\u7684\u5916\u534a\u5f84");
    model.param().set("Dpml", "0.2[m]");
    model.param().descr("Dpml", "PML \u5bbd\u5ea6");
    model.param().set("r2", "1[m] +Dpml");
    model.param().descr("r2", "\u8ba1\u7b97\u57df\u7684\u5916\u534a\u5f84");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u5c5e\u6027\u548c\u4eff\u771f\u53c2\u6570");
    model.param("par2").set("lambda", "2.3[Pa]");
    model.param("par2").descr("lambda", "\u4e00\u9636 Lam\u00e9 \u5e38\u6570");
    model.param("par2").set("mu", "1[Pa]");
    model.param("par2").descr("mu", "\u4e8c\u9636 Lam\u00e9 \u5e38\u6570");
    model.param("par2").set("rho", "1[kg/m^3]");
    model.param("par2").descr("rho", "\u5bc6\u5ea6");
    model.param("par2").set("cP", "sqrt((lambda+2*mu)/rho)");
    model.param("par2").descr("cP", "\u901f\u5ea6 P \u6ce2");
    model.param("par2").set("cS", "sqrt(mu/rho)");
    model.param("par2").descr("cS", "\u901f\u5ea6 S \u6ce2");
    model.param("par2").set("omega", "40[rad/s]");
    model.param("par2").descr("omega", "\u5706\u5468\u9891\u7387");
    model.param("par2").set("kappaP", "omega/cP");
    model.param("par2").descr("kappaP", "\u6ce2\u6570 P \u6ce2");
    model.param("par2").set("kappaS", "omega/cS");
    model.param("par2").descr("kappaS", "\u6ce2\u6570 S \u6ce2");
    model.param("par2").set("wlengthP", "2*pi/kappaP");
    model.param("par2").descr("wlengthP", "\u6ce2\u957f P \u6ce2");
    model.param("par2").set("wlengthS", "2*pi/kappaS");
    model.param("par2").descr("wlengthS", "\u6ce2\u957f S \u6ce2");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r2");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "Dpml", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "r2-Dpml-r1", 1);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "r1-r0", 2);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-((r2-Dpml-r1)/2+r1)*cos(pi/4)", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "((r2-Dpml-r1)/2+r1)*sin(pi/4)", 1);
    model.component("comp1").geom("geom1").run("pt1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("PML");
    model.component("comp1").selection("sel1").set(1, 2, 7, 12);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6597\u7bf7");
    model.component("comp1").selection("sel2").set(5, 6, 9, 10);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u80cc\u666f\u5b9e\u4f53");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u80cc\u666f\u548c\u6597\u7bf7");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "com1"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel1");
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("solid").feature("lemm1").set("IsotropicOption", "Lame");
    model.component("comp1").physics("solid").feature("lemm1").set("lambLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("lambLame", "lambda");
    model.component("comp1").physics("solid").feature("lemm1").set("muLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("muLame", "mu");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho");
    model.component("comp1").physics("solid").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("solid").feature("pl1").selection().set(3);
    model.component("comp1").physics("solid").feature("pl1").set("forcePoint", new int[]{1, 0, 0});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "wlengthS/12");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 7);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hmax", "wlengthS/25");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(19, 20, 24, 25);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 20);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 5);

    model.study("std1").label("\u81ea\u7531\u573a");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "omega/2/pi");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\u573a");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").set("title", "\u4f4d\u79fb\u5927\u5c0f (m)");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u81ea\u7531\u573a");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 0);
    model.result("pg1").feature("surf1").set("rangecolormax", 0.15);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("P \u6ce2");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("paramindicator", "");
    model.result("pg2").set("title", "P \u6ce2\uff08\u4f53\u79ef\u5e94\u53d8\uff09");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u81ea\u7531\u573a");
    model.result("pg2").feature("surf1").set("expr", "solid.evol");
    model.result("pg2").feature("surf1").set("descr", "\u4f53\u79ef\u5e94\u53d8");
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", -1);
    model.result("pg2").feature("surf1").set("rangecolormax", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("S \u6ce2");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").set("title", "S \u6ce2\uff08\u4f4d\u79fb\u65cb\u5ea6\uff0cZ \u5206\u91cf\uff09");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.curlUZ");
    model.result("pg3").feature("surf1").set("descr", "\u4f4d\u79fb\u65cb\u5ea6\uff0cZ \u5206\u91cf");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -5);
    model.result("pg3").feature("surf1").set("rangecolormax", 5);
    model.result("pg3").run();

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem("sys2").set("frametype", "material");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("aa", "(sys2.r-r0)/sys2.r");
    model.component("comp1").variable("var1").set("bb", "(r1/(r1-r0))^2");
    model.component("comp1").variable("var1")
         .set("Ccl_rrrr", "(lambda+2*mu)*aa", "\u67f1\u5750\u6807\u7cfb\u4e2d\u7684\u5f39\u6027\u6a21\u91cf");
    model.component("comp1").variable("var1").set("Ccl_tttt", "(lambda+2*mu)/aa");
    model.component("comp1").variable("var1").set("Ccl_rrtt", "lambda");
    model.component("comp1").variable("var1").set("Ccl_ttrr", "lambda");
    model.component("comp1").variable("var1").set("Ccl_rtrt", "mu*aa");
    model.component("comp1").variable("var1").set("Ccl_rttr", "mu");
    model.component("comp1").variable("var1").set("Ccl_trrt", "mu");
    model.component("comp1").variable("var1").set("Ccl_trtr", "mu/aa");
    model.component("comp1").variable("var1")
         .set("Ccl_1111", "Ccl_rrrr*(cos(sys2.phi))^4+(Ccl_rrtt+Ccl_rtrt+Ccl_rttr+Ccl_trrt+Ccl_trtr+Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_tttt*(sin(sys2.phi))^4", "\u7b1b\u5361\u5c14\u5750\u6807\u7cfb\u4e2d\u7684\u5f39\u6027\u6a21\u91cf");
    model.component("comp1").variable("var1")
         .set("Ccl_1112", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rrrr-Ccl_rrtt-Ccl_rtrt-Ccl_trrt)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rttr+Ccl_trtr+Ccl_ttrr-Ccl_tttt)");
    model.component("comp1").variable("var1")
         .set("Ccl_1121", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rrrr-Ccl_rrtt-Ccl_rttr-Ccl_trtr)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rtrt+Ccl_ttrr+Ccl_trrt-Ccl_tttt)");
    model.component("comp1").variable("var1")
         .set("Ccl_1122", "Ccl_rrtt*(cos(sys2.phi))^4+(Ccl_rrrr-Ccl_rtrt-Ccl_rttr-Ccl_trrt-Ccl_trtr+Ccl_tttt)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_ttrr*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_1211", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rrrr-Ccl_rtrt-Ccl_rttr-Ccl_ttrr)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rrtt+Ccl_trrt+Ccl_trtr-Ccl_tttt)");
    model.component("comp1").variable("var1")
         .set("Ccl_1212", "Ccl_rtrt*(cos(sys2.phi))^4+(Ccl_rrrr+Ccl_tttt-Ccl_rrtt-Ccl_rttr-Ccl_trrt-Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_trtr*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_1221", "Ccl_rttr*(cos(sys2.phi))^4+(Ccl_rrrr+Ccl_tttt-Ccl_rrtt-Ccl_rtrt-Ccl_trtr-Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_trrt*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_1222", "(cos(sys2.phi))^3*sin(sys2.phi)*(-Ccl_tttt+Ccl_rrtt+Ccl_rtrt+Ccl_rttr)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rrrr-Ccl_trtr-Ccl_ttrr-Ccl_trrt)");
    model.component("comp1").variable("var1")
         .set("Ccl_2111", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rrrr-Ccl_trtr-Ccl_ttrr-Ccl_trrt)+(sin(sys2.phi))^3*cos(sys2.phi)*(-Ccl_tttt+Ccl_rrtt+Ccl_rtrt+Ccl_rttr)");
    model.component("comp1").variable("var1")
         .set("Ccl_2112", "Ccl_trrt*(cos(sys2.phi))^4+(Ccl_rrrr+Ccl_tttt-Ccl_rrtt-Ccl_rtrt-Ccl_trtr-Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_rttr*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_2121", "Ccl_trtr*(cos(sys2.phi))^4+(Ccl_rrrr+Ccl_tttt-Ccl_rrtt-Ccl_rttr-Ccl_trrt-Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_rtrt*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_2122", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rrtt+Ccl_trrt+Ccl_trtr-Ccl_tttt)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rrrr-Ccl_rtrt-Ccl_rttr-Ccl_ttrr)");
    model.component("comp1").variable("var1")
         .set("Ccl_2211", "Ccl_ttrr*(cos(sys2.phi))^4+(Ccl_rrrr-Ccl_rtrt-Ccl_rttr-Ccl_trrt-Ccl_trtr+Ccl_tttt)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_rrtt*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1")
         .set("Ccl_2212", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rtrt+Ccl_ttrr+Ccl_trrt-Ccl_tttt)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rrrr-Ccl_rrtt-Ccl_rttr-Ccl_trtr)");
    model.component("comp1").variable("var1")
         .set("Ccl_2221", "(cos(sys2.phi))^3*sin(sys2.phi)*(Ccl_rttr+Ccl_trtr+Ccl_ttrr-Ccl_tttt)+(sin(sys2.phi))^3*cos(sys2.phi)*(Ccl_rrrr-Ccl_rrtt-Ccl_rtrt-Ccl_trrt)");
    model.component("comp1").variable("var1")
         .set("Ccl_2222", "Ccl_tttt*(cos(sys2.phi))^4+(Ccl_rrtt+Ccl_rtrt+Ccl_rttr+Ccl_trrt+Ccl_trtr+Ccl_ttrr)*(sin(sys2.phi))^2*(cos(sys2.phi))^2+Ccl_rrrr*(sin(sys2.phi))^4");
    model.component("comp1").variable("var1").set("rhocl", "aa*bb*rho", "\u5bc6\u5ea6");
    model.component("comp1").variable("var1").label("\u6597\u7bf7\u6750\u6599\u5c5e\u6027");

    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 2);
    model.component("comp1").physics("solid").feature("lemm2").label("\u6597\u7bf7\u7ebf\u5f39\u6027\u6750\u6599");
    model.component("comp1").physics("solid").feature("lemm2").selection().named("sel2");
    model.component("comp1").physics("solid").feature("lemm2").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("solid").feature("lemm2").set("D_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2")
         .set("D", new String[]{"Ccl_1111", "Ccl_1122", "0", "0", "0", "0", "Ccl_1122", "Ccl_2222", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("lemm2").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").set("rho", "rhocl");
    model.component("comp1").physics("solid").feature("lemm2").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("lemm2").feature("exs1")
         .set("StressInputType", "StressTensorNominal");
    model.component("comp1").physics("solid").feature("lemm2").feature("exs1")
         .set("Pext", new String[]{"Ccl_1121*solid.gradUxY+Ccl_1112*solid.gradUyX", "Ccl_1211*solid.gradUxX+Ccl_1221*solid.gradUxY+Ccl_1212*solid.gradUyX+Ccl_1222*solid.gradUyY", "0", "Ccl_2111*solid.gradUxX+Ccl_2121*solid.gradUxY+Ccl_2112*solid.gradUyX+Ccl_2122*solid.gradUyY", "Ccl_2221*solid.gradUxY+Ccl_2212*solid.gradUyX", "0", "0", "0", "0"});

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u6597\u7bf7");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "omega/2/pi");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").label("\u6597\u7bf7");
    model.result("pg1").feature("surf2").set("data", "dset2");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("arraydim", "1");
    model.result("pg1").feature("ann1").label("\u6ce8\u91ca\uff0c\u81ea\u7531\u573a");
    model.result("pg1").feature("ann1").set("text", "\u81ea\u7531\u573a");
    model.result("pg1").feature("ann1").set("posxexpr", 0.02);
    model.result("pg1").feature("ann1").set("posyexpr", 1.2);
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("anchorpoint", "center");
    model.result("pg1").feature("ann1").set("manualindexing", true);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").feature("ann2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").label("\u6ce8\u91ca\uff0c\u6597\u7bf7");
    model.result("pg1").feature("ann2").set("text", "\u6597\u7bf7");
    model.result("pg1").feature("ann2").set("arrayindex", 1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").label("\u6597\u7bf7");
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg1").feature("ann1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().copy("ann1", "pg1/ann1");
    model.result("pg2").feature().copy("ann2", "pg1/ann2");
    model.result("pg2").feature("ann1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").label("\u81ea\u7531\u573a");
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").label("\u6597\u7bf7");
    model.result("pg3").feature("surf2").set("data", "dset2");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg2").feature("ann1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("ann1", "pg2/ann1");
    model.result("pg3").feature().copy("ann2", "pg2/ann2");
    model.result("pg3").feature("ann1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").run();

    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"solid/lemm2"});

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7f29\u7565\u56fe");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").feature().copy("surf3", "pg2/surf1");
    model.result("pg4").feature().copy("surf4", "pg2/surf2");
    model.result("pg4").feature().copy("ann3", "pg2/ann1");
    model.result("pg4").feature().copy("ann4", "pg2/ann2");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("arrayshape", "square");
    model.result("pg4").feature("ann2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").set("colindex", 1);
    model.result("pg4").run();
    model.result("pg4").feature("ann3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann3").set("rowindex", 1);
    model.result("pg4").feature("ann4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann4").set("rowindex", 1);
    model.result("pg4").feature("ann4").set("colindex", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("ann5", "Annotation");
    model.result("pg4").feature("ann5").set("arraydim", "2");
    model.result("pg4").feature("ann5").set("text", "P \u6ce2");
    model.result("pg4").feature("ann5").set("posxexpr", -1.5);
    model.result("pg4").feature("ann5").set("showpoint", false);
    model.result("pg4").feature("ann5").set("anchorpoint", "center");
    model.result("pg4").feature("ann5").set("manualindexing", true);
    model.result("pg4").feature("ann5").set("rowindex", 1);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("ann6", "ann5");
    model.result("pg4").feature("ann6").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("ann6").set("text", "S \u6ce2");
    model.result("pg4").feature("ann6").set("rowindex", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u622a\u7ebf\uff0c\u81ea\u7531\u573a");
    model.result().dataset("cln1").setIndex("genpoints", "r1*cos(pi/4)", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "(r2-Dpml)*cos(pi/4)", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-r1*sin(pi/4)", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "-(r2-Dpml)*sin(pi/4)", 1, 1);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").label("\u622a\u7ebf\uff0c\u6597\u7bf7");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().dataset("cln2").setIndex("genpoints", "r1*cos(pi/4)", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "(r2-Dpml)*cos(pi/4)", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "-r1*sin(pi/4)", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "-(r2-Dpml)*sin(pi/4)", 1, 1);
    model.result("pg1").run();

    model.title("\u6781\u6027\u6750\u6599\u5f39\u6027\u6597\u7bf7");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u5916\u90e8\u5e94\u529b\u201d\u7279\u5f81\u6765\u5efa\u7acb\u5f39\u6027\u9690\u5f62\u6597\u7bf7\u8bbe\u8ba1\u6240\u9700\u7684\u975e\u5bf9\u79f0\u5e94\u529b\u6750\u6599\u6a21\u578b\u3002\u8be5\u88c5\u7f6e\u7684\u76ee\u7684\u662f\u6709\u6548\u5c4f\u853d\u7a7a\u95f4\u533a\u57df\uff0c\u4f7f\u5176\u514d\u53d7 P \u6ce2\u548c S \u6ce2\u7684\u5e72\u6270\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("elastic_cloaking.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
