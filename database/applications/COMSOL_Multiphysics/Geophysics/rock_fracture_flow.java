/*
 * rock_fracture_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class rock_fracture_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Geophysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cdeq", "ConvectionDiffusionEquation", "geom1");
    model.component("comp1").physics("cdeq").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("cdeq").prop("Units").set("DependentVariableQuantity", "head");
    model.component("comp1").physics("cdeq").field("dimensionless").field("H");
    model.component("comp1").physics("cdeq").prop("Units").set("SourceTermQuantity", "timechangeinpressurehead");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cdeq", true);

    model.param().set("rho", "1000[kg/m^3]");
    model.param().descr("rho", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu", "0.001[Pa*s]");
    model.param().descr("mu", "\u6d41\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "rock_fracture_flow_aperture_data.txt");
    model.func("int1").importData();
    model.func("int1").setIndex("funcnametable", "data", 0, 0);
    model.func("int1").setIndex("argunit", "mm", 0);
    model.func("int1").setIndex("argunit", "mm", 1);
    model.func("int1").setIndex("fununit", "mm", 0);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{80, 50});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{10, 20});
    model.component("comp1").geom("geom1").run("r1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("a", "data(x,y)/1000");
    model.component("comp1").variable("var1").descr("a", "\u7f1d\u9699");
    model.component("comp1").variable("var1").set("Ks", "a^2*rho*g_const/mu");
    model.component("comp1").variable("var1").descr("Ks", "\u6c34\u529b\u4f20\u5bfc\u7387");
    model.component("comp1").variable("var1").set("Ts", "Ks*a");
    model.component("comp1").variable("var1").descr("Ts", "\u5bfc\u6c34\u7cfb\u6570");
    model.component("comp1").variable("var1").set("u", "-Ks*Hx");
    model.component("comp1").variable("var1").descr("u", "\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("v", "-Ks*Hy");
    model.component("comp1").variable("var1").descr("v", "\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("U", "sqrt(u^2+v^2)");
    model.component("comp1").variable("var1").descr("U", "\u901f\u5ea6\u5927\u5c0f");

    model.component("comp1").physics("cdeq").feature("cdeq1").setIndex("c", new String[]{"Ts", "0", "0", "Ts"}, 0);
    model.component("comp1").physics("cdeq").feature("cdeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("cdeq").feature("cdeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("cdeq").create("dir1", "DirichletBoundary", 1);
    model.component("comp1").physics("cdeq").feature("dir1").selection().set(2);
    model.component("comp1").physics("cdeq").create("dir2", "DirichletBoundary", 1);
    model.component("comp1").physics("cdeq").feature("dir2").selection().set(3);
    model.component("comp1").physics("cdeq").feature("dir2").setIndex("r", "20[mm]", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u5bf9\u6d41-\u6269\u6563\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "H");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6 (2D)");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "U");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("hght1").set("heightdata", "expr");
    model.result("pg1").feature("surf1").feature("hght1").set("expr", "data(x,y)");
    model.result("pg1").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("hght1").set("scale", 1);
    model.result("pg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("ps1", "ParametricSurface");
    model.component("comp2").geom("geom2").feature("ps1").set("parmin1", 10);
    model.component("comp2").geom("geom2").feature("ps1").set("parmax1", 90);
    model.component("comp2").geom("geom2").feature("ps1").set("parmin2", 20);
    model.component("comp2").geom("geom2").feature("ps1").set("parmax2", 70);
    model.component("comp2").geom("geom2").feature("ps1").set("coord", new String[]{"s1", "s2", "data(s1,s2)"});
    model.component("comp2").geom("geom2").feature("ps1").set("rtol", "1.0E-2");
    model.component("comp2").geom("geom2").feature("ps1").set("maxknots", 100);
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").variable().create("var2");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").variable("var2").set("a", "data(x,y)/1000", "\u7f1d\u9699");
    model.component("comp2").variable("var2").set("Ks", "a^2*rho*g_const/mu", "\u6c34\u529b\u4f20\u5bfc\u7387");
    model.component("comp2").variable("var2").set("Ts", "a*Ks", "\u5bfc\u6c34\u7cfb\u6570");
    model.component("comp2").variable("var2").set("u", "-Ks*H2Tx", "\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp2").variable("var2").set("v", "-Ks*H2Ty", "\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp2").variable("var2").set("w", "-Ks*H2Tz");
    model.component("comp2").variable("var2").descr("w", "\u901f\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp2").variable("var2").set("U", "sqrt(u^2+v^2+w^2)", "\u901f\u5ea6\u5927\u5c0f");

    model.component("comp2").physics().create("cb", "CoefficientFormBoundaryPDE", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/cb", false);

    model.component("comp2").physics("cb").prop("EquationForm").set("form", "Automatic");
    model.component("comp2").physics("cb").prop("Units").set("DependentVariableQuantity", "head");
    model.component("comp2").physics("cb").field("dimensionless").field("H2");
    model.component("comp2").physics("cb").field("dimensionless").component(new String[]{"H2"});
    model.component("comp2").physics("cb").prop("Units").set("SourceTermQuantity", "timechangeinpressurehead");
    model.component("comp2").physics("cb").feature("cfeq1")
         .setIndex("c", new String[]{"-Ts", "0", "0", "0", "-Ts", "0", "0", "0", "-Ts"}, 0);
    model.component("comp2").physics("cb").feature("cfeq1").setIndex("f", 0, 0);
    model.component("comp2").physics("cb").feature("cfeq1").setIndex("da", 0, 0);
    model.component("comp2").physics("cb").create("dir1", "DirichletBoundary", 1);
    model.component("comp2").physics("cb").feature("dir1").selection().set(2);
    model.component("comp2").physics("cb").create("dir2", "DirichletBoundary", 1);
    model.component("comp2").physics("cb").feature("dir2").selection().set(3);
    model.component("comp2").physics("cb").feature("dir2").setIndex("r", "20[mm]", 0);

    model.component("comp2").mesh("mesh2").autoMeshSize(7);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/cdeq", false);
    model.study("std2").feature("stat").setSolveFor("/physics/cb", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").label("\u7cfb\u6570\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg2").feature("surf1").set("expr", "H2");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6\uff08\u4e09\u7ef4\uff09");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "U");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").set("arrowlength", "normalized");
    model.result("pg2").feature("arws1").set("placement", "elements");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5ca9\u77f3\u88c2\u9699\u6d41");

    model
         .description("\u5ca9\u77f3\u88c2\u9699\u4e2d\u7684\u6d41\u52a8\u5728\u4e8c\u7ef4\u548c\u4e09\u7ef4\u4e2d\u5efa\u6a21\u3002\u672c\u4f8b\u4f7f\u7528\u7efc\u5408\u751f\u6210\u7684\u88c2\u9699\u5b54\u9699\u5ea6\uff08\u5bbd\u5ea6\uff09\u6570\u636e\uff0c\u901a\u8fc7\u63d2\u503c\u51fd\u6570\u5bfc\u5165\uff1b\u5e76\u5047\u5b9a\u6c34\u529b\u4f20\u5bfc\u7387\u4e0e\u5b54\u9699\u5ea6\u6210\u7acb\u65b9\u5173\u7cfb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rock_fracture_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
