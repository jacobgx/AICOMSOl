/*
 * transport_and_adsorption.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class transport_and_adsorption {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Chemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("gb", "GeneralFormBoundaryPDE", "geom1");
    model.component("comp1").physics("gb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("gb").field("dimensionless").component(new String[]{"cs"});
    model.component("comp1").physics("gb").feature("gfeq1").set("Ga", new String[][]{{"-csTx", "-csTy"}});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/gb", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c0", "1000[mol/m^3]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("k_ads", "1e-6[m^3/(mol*s)]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("k_des", "1e-9[1/s]", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("Gamma_s", "1000[mol/m^2]", "\u6d3b\u6027\u4f4d\u6d53\u5ea6");
    model.param().set("Ds", "1e-11[m^2/s]", "\u8868\u9762\u6269\u6563\u7cfb\u6570");
    model.param().set("D", "1e-9[m^2/s]", "\u6c14\u4f53\u6269\u6563\u7cfb\u6570");
    model.param().set("v_max", "1[mm/s]", "\u6700\u5927\u901f\u5ea6");
    model.param().set("delta", "0.1[mm]", "\u901a\u9053\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.1, 0.3});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -0.1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.1, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.1, 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.1, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(5);
    model.component("comp1").variable("var1").set("R", "k_ads*c*(Gamma_s-cs)-k_des*cs");
    model.component("comp1").variable("var1").descr("R", "\u8868\u9762\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(1);
    model.component("comp1").variable("var2").set("v_lam", "v_max*(1-((x-0.5*delta)/(0.5*delta))^2)");
    model.component("comp1").variable("var2").descr("v_lam", "\u5165\u53e3\u901f\u5ea6\u5206\u5e03");

    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"0", "v_lam", "0"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c0", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().set(5);
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("J0", "-R", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(3);
    model.component("comp1").physics("tds").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("tds").feature("sym1").selection().set(1, 4, 6);
    model.component("comp1").physics("gb").selection().set(5);
    model.component("comp1").physics("gb").prop("Units").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("gb").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("gb").prop("Units").setIndex("CustomDependentVariableUnit", "mol/m^2", 0, 0);
    model.component("comp1").physics("gb").prop("Units").setIndex("CustomSourceTermUnit", "mol/(m^2*s)", 0, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("Ga", new String[]{"-csTx*Ds", "-csTy"}, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("Ga", new String[]{"-csTx*Ds", "-csTy*Ds"}, 0);
    model.component("comp1").physics("gb").feature("gfeq1").setIndex("f", "R", 0);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1.5[um]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,2)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").create("line1", "Line");
    model.result("pg2").label("\u4e00\u822c\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg2").feature("line1").set("expr", "cs");
    model.result("pg1").run();
    model.result("pg1").label("\u53cd\u5e94\u5668\u4e2d\u7684\u7269\u8d28\u6d53\u5ea6");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6cbf\u6d3b\u6027\u8868\u9762\u7684\u53cd\u5e94\u7269\u6d53\u5ea6");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{41}, 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(5);
    model.result("pg3").feature("lngr1").set("xdataexpr", "y");
    model.result("pg3").feature("lngr1").set("xdatadescr", "y \u5750\u6807");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6cbf\u6d3b\u6027\u8868\u9762\u5438\u9644\u7269\u8d28\u7684\u6d53\u5ea6");
    model.result("pg4").setIndex("looplevel", new int[]{2, 11, 21, 31, 41}, 0);
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "cs");
    model.result("pg4").feature("lngr1").set("descr", "\u56e0\u53d8\u91cf cs");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6cbf\u6d3b\u6027\u8868\u9762\u7684\u8868\u9762\u53cd\u5e94\u901f\u7387");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "R");
    model.result("pg5").feature("lngr1").set("descr", "\u8868\u9762\u53cd\u5e94\u901f\u7387");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6d3b\u6027\u8868\u9762\u4e0a\u5438\u9644\u7269\u8d28\u7684\u6d53\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 0.005);
    model.result("pg2").run();

    model.title("\u4f20\u9012\u548c\u5438\u9644");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u8868\u9762\u6269\u6563\u548c\u8868\u9762\u53cd\u5e94\u4e0e\u53cd\u5e94\u8868\u9762\u7684\u7269\u8d28\u4f20\u9012\u76f8\u8026\u5408\u7684\u60c5\u51b5\u3002\u5728\u5438\u9644\u53cd\u5e94\u4e2d\uff0c\u9700\u8981\u6a21\u62df\u6d3b\u6027\u4f4d\u6216\u8868\u9762\u5316\u5408\u7269\u7684\u8868\u9762\u6d53\u5ea6\uff0c\u4ee5\u53ca\u6c14\u76f8\u672c\u4f53\u6d53\u5ea6\u3002\u8be5\u88c5\u7f6e\u53ef\u4ee5\u662f\u50ac\u5316\u5242\u3001\u751f\u7269\u82af\u7247\u3001\u534a\u5bfc\u4f53\u5143\u4ef6\u6216\u5305\u542b\u8868\u9762\u7279\u5b9a\u7269\u8d28\u7684\u4efb\u4f55\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("transport_and_adsorption.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
