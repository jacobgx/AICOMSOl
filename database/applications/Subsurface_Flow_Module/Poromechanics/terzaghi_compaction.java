/*
 * terzaghi_compaction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:31 by COMSOL 6.3.0.290. */
public class terzaghi_compaction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Poromechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5200, 440});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -440});
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 20, 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 20, 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1200, 320});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{4000, -440});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1, 3);
    model.component("comp1").variable("var1").set("S_sk", "1e-5[m^-1]");
    model.component("comp1").variable("var1").descr("S_sk", "\u571f\u4f53\u9aa8\u67b6\u6bd4\u50a8\u6c34\u7387");
    model.component("comp1").variable("var1").set("K_s", "25[m/day]");
    model.component("comp1").variable("var1").descr("K_s", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(2);
    model.component("comp1").variable("var2")
         .set("S_sk", "1e-4[m^-1]", "\u571f\u4f53\u9aa8\u67b6\u6bd4\u50a8\u6c34\u7387");
    model.component("comp1").variable("var2")
         .set("K_s", "0.01[m/day]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set(1, 3);
    model.component("comp1").material("pmat1").set("porosity", "0.25");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material().duplicate("pmat2", "pmat1");
    model.component("comp1").material("pmat2").selection().set(2);
    model.component("comp1").material("pmat2").set("porosity", "0.025");

    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");
    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("porous1").set("Sp", "S_sk/dl.rho/g_const");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1")
         .set("fluidType", "compressibleLinearized");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("K", new String[]{"K_s", "0", "0", "0", "K_s", "0", "0", "0", "K_s"});

    model.material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.material("mat1").propertyGroup("def").set("compressibility", new String[]{"4e-10"});

    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh1").selection().set(1);
    model.component("comp1").physics("dl").feature("hh1").set("H0", "-6[m/year]*t");
    model.component("comp1").physics("dl").create("hh2", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh2").selection().set(5, 7, 12);

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().all();

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("b", "genproj1(-dl.H*(y<=dest(y))*S_sk)");
    model.component("comp1").variable("var3").descr("b", "\u56fa\u7ed3");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("yscale", 10);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,1,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.result("pg1").run();
    model.result("pg1").label("\u6c34\u5934");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u65f6\u95f4 = 10 \u5e74\uff0c\u8868\u9762\uff1a\u6c34\u5934 (m) \u6d41\u7ebf\uff1a\u901f\u5ea6\u573a");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "dl.H");
    model.result("pg1").feature("surf1").set("descr", "\u6c34\u5934");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u56fa\u7ed3");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "b");
    model.result("pg2").feature("con1").set("descr", "\u56fa\u7ed3");
    model.result("pg2").feature("con1").set("number", 15);
    model.result("pg2").feature("con1").set("contourtype", "filled");
    model.result("pg2").feature("con1").set("colortable", "Cividis");
    model.result("pg2").run();

    model.title("Terzaghi \u56fa\u7ed3");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6c42\u89e3\u4f20\u7edf\u7684\u9971\u548c\u6d41\u4f53\u6d41\u52a8\u95ee\u9898\u6765\u7814\u7a76\u6c34\u5934\u53d8\u5316\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u7528\u4e8e\u8bc4\u4f30\u6c89\u79ef\u7269\u7684\u5782\u76f4\u538b\u5b9e\u3002\u8be5\u5206\u6790\u4ee5 Terzaghi \u7406\u8bba\u548c\u6709\u6548\u5e94\u529b\u6982\u5ff5\u4e3a\u57fa\u7840\uff0c\u5e76\u5c06\u6c42\u89e3\u7ed3\u679c\u4e0e\u516c\u5f00\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u901a\u8fc7\u5728\u201cBiot \u591a\u5b54\u5f39\u6027\u201d\u6a21\u578b\u4e2d\u8fdb\u884c\u4fee\u6b63\uff0c\u4ee5\u5206\u6790\u53cc\u5411\u8026\u5408\u7684\u6d41\u4f53\u6d41\u52a8\u548c\u56fa\u4f53\u53d8\u5f62\u3002");

    model.label("terzaghi_compaction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
