/*
 * scordelis_lo_roof.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class scordelis_lo_roof {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 25, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 25, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 25, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", "90+40");
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("shell").feature("to1").set("d", 0.25);
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(3, 4);
    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("shell").feature("disp1").selection().set(1);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().set(1);
    model.component("comp1").physics("shell").feature("fl1").set("forceReferenceArea", new int[]{0, 0, -90});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"4.32e8"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u5e38\u89c4");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg1").run();
    model.result("pg1").label("\u5782\u76f4\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset("dset1").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u5e38\u89c4");

    model.component("comp1").mesh("mesh1").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u5e38\u89c4");
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u5e38\u89c4");
    model.component("comp1").mesh("mesh2").create("map1", "Map");
    model.component("comp1").mesh("mesh2").feature("map1").selection().remaining();
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u5e38\u89c4");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").run();
    model.result().dataset("dset2").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u5e38\u89c4");

    model.component("comp1").mesh().duplicate("mesh3", "mesh2");
    model.component("comp1").mesh("mesh3").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");
    model.component("comp1").mesh("mesh3").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh3").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset3");
    model.result("pg1").run();
    model.result().dataset("dset3").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");

    model.component("comp1").mesh().duplicate("mesh4", "mesh1");
    model.component("comp1").mesh("mesh4").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");
    model.component("comp1").mesh("mesh4").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh4").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std4").label("\u7814\u7a76 4\uff1a\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset4");
    model.result("pg1").run();
    model.result().dataset("dset4").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u8d85\u7ec6");

    model.component("comp1").mesh().duplicate("mesh5", "mesh1");
    model.component("comp1").mesh("mesh5").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u66f4\u7c97");
    model.component("comp1").mesh("mesh5").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh5").run();

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std5").label("\u7814\u7a76 5\uff1a\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u66f4\u7c97");
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset5");
    model.result("pg1").run();
    model.result().dataset("dset5").label("\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u66f4\u7c97");

    model.component("comp1").mesh().duplicate("mesh6", "mesh2");
    model.component("comp1").mesh("mesh6").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u66f4\u7c97");
    model.component("comp1").mesh("mesh6").feature("size").set("hauto", 7);

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std6").label("\u7814\u7a76 6\uff1a\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u66f4\u7c97");
    model.study("std6").setGenPlots(false);
    model.study("std6").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset6");
    model.result("pg1").run();
    model.result().dataset("dset6").label("\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u66f4\u7c97");
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(3);
    model.result().numerical("pev1").setIndex("expr", "w", 0);
    model.result().numerical("pev1")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u5e38\u89c4", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().numerical().duplicate("pev2", "pev1");
    model.result().numerical("pev2").set("data", "dset2");
    model.result().numerical("pev2")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u5e38\u89c4", 0);
    model.result().numerical("pev2").set("table", "tbl1");
    model.result().numerical("pev2").appendResult();
    model.result().numerical().duplicate("pev3", "pev2");
    model.result().numerical("pev3").set("data", "dset3");
    model.result().numerical("pev3")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u8d85\u7ec6", 0);
    model.result().numerical("pev3").set("table", "tbl1");
    model.result().numerical("pev3").appendResult();
    model.result().numerical().duplicate("pev4", "pev3");
    model.result().numerical("pev4").set("data", "dset4");
    model.result().numerical("pev4")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u8d85\u7ec6", 0);
    model.result().numerical("pev4").set("table", "tbl1");
    model.result().numerical("pev4").appendResult();
    model.result().numerical().duplicate("pev5", "pev4");
    model.result().numerical("pev5").set("data", "dset5");
    model.result().numerical("pev5")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u4e09\u89d2\u5f62\u5355\u5143\uff0c\u66f4\u7c97", 0);
    model.result().numerical("pev5").set("table", "tbl1");
    model.result().numerical("pev5").appendResult();
    model.result().numerical().duplicate("pev6", "pev5");
    model.result().numerical("pev6").set("data", "dset6");
    model.result().numerical("pev6")
         .setIndex("descr", "\u4e2d\u70b9\u4f4d\u79fb\uff0c\u56db\u8fb9\u5f62\u5355\u5143\uff0c\u66f4\u7c97", 0);
    model.result().numerical("pev6").set("table", "tbl1");
    model.result().numerical("pev6").appendResult();
    model.result("pg1").run();

    model.title("Scordelis-Lo \u5c4b\u9762\u58f3\u4f53\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u7684\u201c\u58f3\u201d\u63a5\u53e3\u6784\u5efa\u5e76\u6c42\u89e3\u4e09\u7ef4\u58f3\u6a21\u578b\uff0c\u5e76\u63d0\u4f9b\u4eff\u771f\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u7684\u6bd4\u8f83\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("scordelis_lo_roof.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
