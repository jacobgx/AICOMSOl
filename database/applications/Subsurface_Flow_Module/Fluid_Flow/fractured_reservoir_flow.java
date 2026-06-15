/*
 * fractured_reservoir_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:28 by COMSOL 6.3.0.290. */
public class fractured_reservoir_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);

    model.component("comp1").geom("geom1").insertFile("fractured_reservoir_flow_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh().create("tempmesh1");
    model.component("comp1").mesh("tempmesh1").run();

    model.result().dataset().create("tempdset1", "Mesh");
    model.result().dataset("tempdset1").set("mesh", "tempmesh1");
    model.result().numerical().create("tempmin1", "MinVolume");
    model.result().numerical("tempmin1").set("data", "tempdset1");
    model.result().numerical().create("tempmax1", "MaxVolume");
    model.result().numerical("tempmax1").set("data", "tempdset1");
    model.result().numerical("tempmin1").selection().all();
    model.result().numerical("tempmax1").selection().all();
    model.result().numerical("tempmin1").setIndex("expr", "x", 0);
    model.result().numerical("tempmin1").setIndex("expr", "y", 1);
    model.result().numerical("tempmin1").setIndex("expr", "z", 2);
    model.result().numerical("tempmax1").setIndex("expr", "x", 0);
    model.result().numerical("tempmax1").setIndex("expr", "y", 1);
    model.result().numerical("tempmax1").setIndex("expr", "z", 2);
    model.result().numerical("tempmin1").computeResult();
    model.result().numerical("tempmax1").computeResult();

    model.component("comp1").mesh().remove("tempmesh1");
    model.component("comp1").mesh().create("tempmesh1");
    model.component("comp1").mesh("tempmesh1").run();

    model.result().dataset().create("tempdset1", "Mesh");
    model.result().dataset("tempdset1").set("mesh", "tempmesh1");
    model.result().numerical().create("tempmin1", "MinVolume");
    model.result().numerical("tempmin1").set("data", "tempdset1");
    model.result().numerical().create("tempmax1", "MaxVolume");
    model.result().numerical("tempmax1").set("data", "tempdset1");
    model.result().numerical("tempmin1").selection().named("geom1_sel2");
    model.result().numerical("tempmax1").selection().named("geom1_sel2");
    model.result().numerical("tempmin1").setIndex("expr", "x", 0);
    model.result().numerical("tempmin1").setIndex("expr", "y", 1);
    model.result().numerical("tempmin1").setIndex("expr", "z", 2);
    model.result().numerical("tempmax1").setIndex("expr", "x", 0);
    model.result().numerical("tempmax1").setIndex("expr", "y", 1);
    model.result().numerical("tempmax1").setIndex("expr", "z", 2);
    model.result().numerical("tempmin1").computeResult();
    model.result().numerical("tempmax1").computeResult();

    model.component("comp1").mesh().remove("tempmesh1");

    model.geom().create("dfnpart1", "Part", 3);
    model.geom("dfnpart1").label("3D Fracture Network 1");

    model.param().set("dfn_fractureSize", "5[m]");
    model.param().set("dfn_fractureSizeMin", "200[m]");
    model.param().set("dfn_fractureSizeMax", "500[m]");
    model.param().set("dfn_size_x", "1000.00");
    model.param().set("dfn_size_y", "1000.00");
    model.param().set("dfn_size_z", "354.90");
    model.param().set("dfn_strike", "30[deg]");
    model.param().set("dfn_dip", "85[deg]");

    model.geom("dfnpart1").create("dfnpart1wp1", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp1").label("Fracture 1");
    model.geom("dfnpart1").feature("dfnpart1wp1").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp1").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp1")
         .set("transdispl", new double[]{950.8948292356945, 915.8850293688172, 169.26540178196032});
    model.geom("dfnpart1").feature("dfnpart1wp1").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp1")
         .set("transeulerang", new double[]{-15.616630161333092, 81.39237476696654, 0});
    model.geom("dfnpart1").feature("dfnpart1wp1").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp1").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp1").geom().feature("e1")
         .set("semiaxes", new double[]{426.87497827209967, 464.4543955044729});
    model.geom("dfnpart1").create("dfnpart1wp2", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp2").label("Fracture 2");
    model.geom("dfnpart1").feature("dfnpart1wp2").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp2").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp2")
         .set("transdispl", new double[]{379.08785836512646, 55.18796153371896, 289.4591704980231});
    model.geom("dfnpart1").feature("dfnpart1wp2").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp2")
         .set("transeulerang", new double[]{-23.42599208190939, 73.48805632013988, 0});
    model.geom("dfnpart1").feature("dfnpart1wp2").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp2").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp2").geom().feature("e1")
         .set("semiaxes", new double[]{371.6183187548705, 200});
    model.geom("dfnpart1").create("dfnpart1wp3", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp3").label("Fracture 3");
    model.geom("dfnpart1").feature("dfnpart1wp3").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp3").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp3")
         .set("transdispl", new double[]{162.16221529528318, 788.5519233236041, 257.63265304070256});
    model.geom("dfnpart1").feature("dfnpart1wp3").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp3")
         .set("transeulerang", new double[]{-21.70082071401351, 72.79381308722472, 0});
    model.geom("dfnpart1").feature("dfnpart1wp3").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp3").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp3").geom().feature("e1")
         .set("semiaxes", new double[]{254.87160985959213, 186.80386257734045});
    model.geom("dfnpart1").create("dfnpart1wp4", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp4").label("Fracture 4");
    model.geom("dfnpart1").feature("dfnpart1wp4").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp4").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp4")
         .set("transdispl", new double[]{920.2550763776844, 600.18850427138, 118.81927767076782});
    model.geom("dfnpart1").feature("dfnpart1wp4").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp4")
         .set("transeulerang", new double[]{-7.372575980465946, 82.75440396564301, 0});
    model.geom("dfnpart1").feature("dfnpart1wp4").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp4").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp4").geom().feature("e1")
         .set("semiaxes", new double[]{262.2534395863232, 438.6001480563894});
    model.geom("dfnpart1").create("dfnpart1wp5", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp5").label("Fracture 5");
    model.geom("dfnpart1").feature("dfnpart1wp5").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp5").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp5")
         .set("transdispl", new double[]{492.58891991541157, 436.95934165205273, 162.99878793841634});
    model.geom("dfnpart1").feature("dfnpart1wp5").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp5")
         .set("transeulerang", new double[]{-18.62121526789972, 80.85799100134689, 0});
    model.geom("dfnpart1").feature("dfnpart1wp5").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp5").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp5").geom().feature("e1")
         .set("semiaxes", new double[]{500, 191.4541832506475});
    model.geom("dfnpart1").create("dfnpart1wp6", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp6").label("Fracture 6");
    model.geom("dfnpart1").feature("dfnpart1wp6").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp6").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp6")
         .set("transdispl", new double[]{605.1435647826094, 997.7416537202641, 348.2014424234286});
    model.geom("dfnpart1").feature("dfnpart1wp6").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp6")
         .set("transeulerang", new double[]{-25.556786302852316, 80.46937465406336, 0});
    model.geom("dfnpart1").feature("dfnpart1wp6").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp6").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp6").geom().feature("e1")
         .set("semiaxes", new double[]{500, 428.84955944033453});
    model.geom("dfnpart1").create("dfnpart1wp7", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp7").label("Fracture 7");
    model.geom("dfnpart1").feature("dfnpart1wp7").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp7").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp7")
         .set("transdispl", new double[]{847.1635293264042, 275.35375561855477, 259.45948176339476});
    model.geom("dfnpart1").feature("dfnpart1wp7").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp7")
         .set("transeulerang", new double[]{-10.49200855705342, 77.02785174756511, 0});
    model.geom("dfnpart1").feature("dfnpart1wp7").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp7").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp7").geom().feature("e1")
         .set("semiaxes", new double[]{500, 156.12039770201477});
    model.geom("dfnpart1").create("dfnpart1wp8", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp8").label("Fracture 8");
    model.geom("dfnpart1").feature("dfnpart1wp8").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp8").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp8")
         .set("transdispl", new double[]{238.6680602100668, 893.9536804585344, 77.28258524973468});
    model.geom("dfnpart1").feature("dfnpart1wp8").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp8")
         .set("transeulerang", new double[]{-21.534260693010005, 79.2887902052268, 0});
    model.geom("dfnpart1").feature("dfnpart1wp8").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp8").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp8").geom().feature("e1")
         .set("semiaxes", new double[]{356.77400091572997, 113.27329678024915});
    model.geom("dfnpart1").create("dfnpart1wp9", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp9").label("Fracture 9");
    model.geom("dfnpart1").feature("dfnpart1wp9").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp9").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp9")
         .set("transdispl", new double[]{693.2211199345265, 371.9214216638455, 220.90905327557545});
    model.geom("dfnpart1").feature("dfnpart1wp9").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp9")
         .set("transeulerang", new double[]{-11.502449805079166, 77.00980876232721, 0});
    model.geom("dfnpart1").feature("dfnpart1wp9").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp9").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp9").geom().feature("e1")
         .set("semiaxes", new double[]{349.2877371437199, 321.1608847732125});
    model.geom("dfnpart1").create("dfnpart1wp10", "WorkPlane");
    model.geom("dfnpart1").feature("dfnpart1wp10").label("Fracture 10");
    model.geom("dfnpart1").feature("dfnpart1wp10").set("unite", true);
    model.geom("dfnpart1").feature("dfnpart1wp10").set("planetype", "transformed");
    model.geom("dfnpart1").feature("dfnpart1wp10")
         .set("transdispl", new double[]{197.34206158195178, 544.4790748061076, 2.0031953576410957});
    model.geom("dfnpart1").feature("dfnpart1wp10").set("transspecify", "eulerang");
    model.geom("dfnpart1").feature("dfnpart1wp10")
         .set("transeulerang", new double[]{-10.150688401641219, 78.83902124439926, 0});
    model.geom("dfnpart1").feature("dfnpart1wp10").set("selresult", true);
    model.geom("dfnpart1").feature("dfnpart1wp10").geom().create("e1", "Ellipse");
    model.geom("dfnpart1").feature("dfnpart1wp10").geom().feature("e1")
         .set("semiaxes", new double[]{344.67453964196284, 383.03559574282787});
    model.geom("dfnpart1").create("uni1", "Union");
    model.geom("dfnpart1").feature("uni1").selection("input").all();
    model.geom("dfnpart1").selection().create("csel1", "CumulativeSelection");
    model.geom("dfnpart1").selection("csel1").label("Fracture network");
    model.geom("dfnpart1").feature("uni1").set("contributeto", "csel1");
    model.geom("dfnpart1").run("uni1");

    model.param().remove("dfn_size_x");
    model.param().remove("dfn_size_y");
    model.param().remove("dfn_size_z");
    model.param().remove("dfn_fractureSize");
    model.param().remove("dfn_fractureSizeMin");
    model.param().remove("dfn_fractureSizeMax");
    model.param().remove("dfn_strike");
    model.param().remove("dfn_dip");

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dfnpi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("dfnpi1").set("part", "dfnpart1");
    model.component("comp1").geom("geom1").feature("dfnpi1").set("displ", new String[]{"-0.00", "-0.00", "-247.57"});
    model.component("comp1").geom("geom1").run("dfnpi1");
    model.component("comp1").geom("geom1").feature().create("dfnpar1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("dfnpar1").selection("domain").named("sel2");
    model.component("comp1").geom("geom1").feature("dfnpar1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("dfnpar1").selection("object").named("dfnpi1_csel1");
    model.component("comp1").geom("geom1").feature("dfnpar1").set("keepobject", false);
    model.component("comp1").geom("geom1").run();

    model.nodeGroup().create("apgrp1", "Definitions", "comp1");
    model.nodeGroup("apgrp1").label("Aperture Data 1");

    model.component("comp1").variable().create("apgrp1fvar1");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar1");

    model.component("comp1").variable("apgrp1fvar1").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar1").selection().named("geom1_dfnpi1_dfnpart1wp1_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar1").set("df", 0.004268749782720997, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar2");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar2");

    model.component("comp1").variable("apgrp1fvar2").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar2").selection().named("geom1_dfnpi1_dfnpart1wp2_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar2").set("df", 0.003716183187548705, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar3");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar3");

    model.component("comp1").variable("apgrp1fvar3").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar3").selection().named("geom1_dfnpi1_dfnpart1wp3_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar3").set("df", 0.0025487160985959213, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar4");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar4");

    model.component("comp1").variable("apgrp1fvar4").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar4").selection().named("geom1_dfnpi1_dfnpart1wp4_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar4").set("df", 0.0026225343958632325, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar5");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar5");

    model.component("comp1").variable("apgrp1fvar5").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar5").selection().named("geom1_dfnpi1_dfnpart1wp5_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar5").set("df", 0.005, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar6");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar6");

    model.component("comp1").variable("apgrp1fvar6").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar6").selection().named("geom1_dfnpi1_dfnpart1wp6_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar6").set("df", 0.005, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar7");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar7");

    model.component("comp1").variable("apgrp1fvar7").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar7").selection().named("geom1_dfnpi1_dfnpart1wp7_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar7").set("df", 0.005, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar8");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar8");

    model.component("comp1").variable("apgrp1fvar8").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar8").selection().named("geom1_dfnpi1_dfnpart1wp8_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar8").set("df", 0.0035677400091573, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar9");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar9");

    model.component("comp1").variable("apgrp1fvar9").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar9").selection().named("geom1_dfnpi1_dfnpart1wp9_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar9").set("df", 0.0034928773714371993, "Aperture");
    model.component("comp1").variable().create("apgrp1fvar10");

    model.nodeGroup("apgrp1").add("variable", "apgrp1fvar10");

    model.component("comp1").variable("apgrp1fvar10").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp1fvar10").selection().named("geom1_dfnpi1_dfnpart1wp10_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp1fvar10").set("df", 0.0034467453964196287, "Aperture");

    model.param().remove("dfn_d_f");
    model.param().remove("dfn_d_f_max");
    model.param().remove("dfn_d_f_min");

    model.component("comp1").physics("dl").create("dfn1", "Fracture", 2);
    model.component("comp1").physics("dl").feature("dfn1").selection().named("geom1_dfnpi1_csel1_bnd");
    model.component("comp1").physics("dl").feature("dfn1").set("df", "df");
    model.component("comp1").physics("dl").feature("dfn1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("dfn1").feature("pm1").set("epsilon", "0.7");
    model.component("comp1").physics("dl").feature("dfn1").feature("pm1").set("permeabilityModelType", "cubicLaw");
    model.component("comp1").physics("dl").feature("dfn1").feature("pm1").set("ff", "1");

    model.component("comp1").geom("geom1").feature("dfnpi1").active(false);
    model.component("comp1").geom("geom1").feature("dfnpar1").active(false);

    model.component("comp1").mesh().create("tempmesh1");
    model.component("comp1").mesh("tempmesh1").run();

    model.result().dataset().create("tempdset1", "Mesh");
    model.result().dataset("tempdset1").set("mesh", "tempmesh1");
    model.result().numerical().create("tempmin1", "MinVolume");
    model.result().numerical("tempmin1").set("data", "tempdset1");
    model.result().numerical().create("tempmax1", "MaxVolume");
    model.result().numerical("tempmax1").set("data", "tempdset1");
    model.result().numerical("tempmin1").selection().named("geom1_sel2");
    model.result().numerical("tempmax1").selection().named("geom1_sel2");
    model.result().numerical("tempmin1").setIndex("expr", "x", 0);
    model.result().numerical("tempmin1").setIndex("expr", "y", 1);
    model.result().numerical("tempmin1").setIndex("expr", "z", 2);
    model.result().numerical("tempmax1").setIndex("expr", "x", 0);
    model.result().numerical("tempmax1").setIndex("expr", "y", 1);
    model.result().numerical("tempmax1").setIndex("expr", "z", 2);
    model.result().numerical("tempmin1").computeResult();
    model.result().numerical("tempmax1").computeResult();

    model.component("comp1").geom("geom1").feature("dfnpi1").active(true);
    model.component("comp1").geom("geom1").feature("dfnpar1").active(true);

    model.component("comp1").mesh().remove("tempmesh1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.geom().create("dfnpart2", "Part", 3);
    model.geom("dfnpart2").label("3D Fracture Network 2");

    model.param().set("dfn_fractureSize", "5[m]");
    model.param().set("dfn_fractureSizeMin", "200[m]");
    model.param().set("dfn_fractureSizeMax", "500[m]");
    model.param().set("dfn_size_x", "1000.00");
    model.param().set("dfn_size_y", "1000.00");
    model.param().set("dfn_size_z", "354.90");
    model.param().set("dfn_strike", "125[deg]");
    model.param().set("dfn_dip", "70[deg]");

    model.geom("dfnpart2").create("dfnpart2wp1", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp1").label("Fracture 1");
    model.geom("dfnpart2").feature("dfnpart2wp1").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp1").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp1")
         .set("transdispl", new double[]{950.9844110078994, 746.3406186469402, 321.7668488578951});
    model.geom("dfnpart2").feature("dfnpart2wp1").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp1")
         .set("transeulerang", new double[]{-109.53615553129684, 67.256689828219, 0});
    model.geom("dfnpart2").feature("dfnpart2wp1").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp1").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp1").geom().feature("e1")
         .set("semiaxes", new double[]{313.08663041079274, 200});
    model.geom("dfnpart2").create("dfnpart2wp2", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp2").label("Fracture 2");
    model.geom("dfnpart2").feature("dfnpart2wp2").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp2").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp2")
         .set("transdispl", new double[]{160.46698003579297, 907.7325871762005, 117.9553461178968});
    model.geom("dfnpart2").feature("dfnpart2wp2").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp2")
         .set("transeulerang", new double[]{-117.97741425690148, 64.01058654470668, 0});
    model.geom("dfnpart2").feature("dfnpart2wp2").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp2").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp2").geom().feature("e1")
         .set("semiaxes", new double[]{500, 323.32417631701213});
    model.geom("dfnpart2").create("dfnpart2wp3", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp3").label("Fracture 3");
    model.geom("dfnpart2").feature("dfnpart2wp3").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp3").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp3")
         .set("transdispl", new double[]{903.3784297240001, 998.5688079279425, 283.6512895182481});
    model.geom("dfnpart2").feature("dfnpart2wp3").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp3")
         .set("transeulerang", new double[]{-118.34848549124906, 63.76221470876157, 0});
    model.geom("dfnpart2").feature("dfnpart2wp3").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp3").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp3").geom().feature("e1")
         .set("semiaxes", new double[]{359.38270964408616, 256.57807315079845});
    model.geom("dfnpart2").create("dfnpart2wp4", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp4").label("Fracture 4");
    model.geom("dfnpart2").feature("dfnpart2wp4").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp4").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp4")
         .set("transdispl", new double[]{902.432138306579, 885.0914760566283, 283.5242943052823});
    model.geom("dfnpart2").feature("dfnpart2wp4").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp4")
         .set("transeulerang", new double[]{-115.1953648480043, 66.69352947858648, 0});
    model.geom("dfnpart2").feature("dfnpart2wp4").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp4").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp4").geom().feature("e1")
         .set("semiaxes", new double[]{239.93001646684715, 259.477361279871});
    model.geom("dfnpart2").create("dfnpart2wp5", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp5").label("Fracture 5");
    model.geom("dfnpart2").feature("dfnpart2wp5").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp5").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp5")
         .set("transdispl", new double[]{117.78007336099749, 636.2729204660882, 123.90103072835312});
    model.geom("dfnpart2").feature("dfnpart2wp5").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp5")
         .set("transeulerang", new double[]{-105.35912566955328, 66.5997896620567, 0});
    model.geom("dfnpart2").feature("dfnpart2wp5").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp5").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp5").geom().feature("e1")
         .set("semiaxes", new double[]{500, 99.16636320578041});
    model.geom("dfnpart2").create("dfnpart2wp6", "WorkPlane");
    model.geom("dfnpart2").feature("dfnpart2wp6").label("Fracture 6");
    model.geom("dfnpart2").feature("dfnpart2wp6").set("unite", true);
    model.geom("dfnpart2").feature("dfnpart2wp6").set("planetype", "transformed");
    model.geom("dfnpart2").feature("dfnpart2wp6")
         .set("transdispl", new double[]{459.95352532275336, 153.37922571080165, 23.07326502444725});
    model.geom("dfnpart2").feature("dfnpart2wp6").set("transspecify", "eulerang");
    model.geom("dfnpart2").feature("dfnpart2wp6")
         .set("transeulerang", new double[]{-121.28850912859671, 63.20253145122541, 0});
    model.geom("dfnpart2").feature("dfnpart2wp6").set("selresult", true);
    model.geom("dfnpart2").feature("dfnpart2wp6").geom().create("e1", "Ellipse");
    model.geom("dfnpart2").feature("dfnpart2wp6").geom().feature("e1")
         .set("semiaxes", new double[]{318.09455942002353, 148.78931516315652});
    model.geom("dfnpart2").create("uni1", "Union");
    model.geom("dfnpart2").feature("uni1").selection("input").all();
    model.geom("dfnpart2").selection().create("csel1", "CumulativeSelection");
    model.geom("dfnpart2").selection("csel1").label("Fracture network");
    model.geom("dfnpart2").feature("uni1").set("contributeto", "csel1");
    model.geom("dfnpart2").run("uni1");

    model.param().remove("dfn_size_x");
    model.param().remove("dfn_size_y");
    model.param().remove("dfn_size_z");
    model.param().remove("dfn_fractureSize");
    model.param().remove("dfn_fractureSizeMin");
    model.param().remove("dfn_fractureSizeMax");
    model.param().remove("dfn_strike");
    model.param().remove("dfn_dip");

    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dfnpi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("dfnpi2").set("part", "dfnpart2");
    model.component("comp1").geom("geom1").feature("dfnpi2").set("displ", new String[]{"-0.00", "-0.00", "-247.57"});
    model.component("comp1").geom("geom1").run("dfnpi2");
    model.component("comp1").geom("geom1").feature().create("dfnpar2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("dfnpar2").selection("domain").named("sel2");
    model.component("comp1").geom("geom1").feature("dfnpar2").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("dfnpar2").selection("object").named("dfnpi2_csel1");
    model.component("comp1").geom("geom1").feature("dfnpar2").set("keepobject", false);
    model.component("comp1").geom("geom1").run();

    model.nodeGroup().create("apgrp2", "Definitions", "comp1");
    model.nodeGroup("apgrp2").label("Aperture Data 2");

    model.component("comp1").variable().create("apgrp2fvar1");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar1");

    model.component("comp1").variable("apgrp2fvar1").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar1").selection().named("geom1_dfnpi2_dfnpart2wp1_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar1").set("df", 0.0031308663041079276, "Aperture");
    model.component("comp1").variable().create("apgrp2fvar2");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar2");

    model.component("comp1").variable("apgrp2fvar2").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar2").selection().named("geom1_dfnpi2_dfnpart2wp2_bnd");

    model.param().set("dfn_d_f", "1[mm]");

    return model;
  }

  public static Model run2(Model model) {
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar2").set("df", 0.005, "Aperture");
    model.component("comp1").variable().create("apgrp2fvar3");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar3");

    model.component("comp1").variable("apgrp2fvar3").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar3").selection().named("geom1_dfnpi2_dfnpart2wp3_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar3").set("df", 0.0035938270964408618, "Aperture");
    model.component("comp1").variable().create("apgrp2fvar4");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar4");

    model.component("comp1").variable("apgrp2fvar4").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar4").selection().named("geom1_dfnpi2_dfnpart2wp4_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar4").set("df", 0.0023993001646684716, "Aperture");
    model.component("comp1").variable().create("apgrp2fvar5");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar5");

    model.component("comp1").variable("apgrp2fvar5").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar5").selection().named("geom1_dfnpi2_dfnpart2wp5_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar5").set("df", 0.005, "Aperture");
    model.component("comp1").variable().create("apgrp2fvar6");

    model.nodeGroup("apgrp2").add("variable", "apgrp2fvar6");

    model.component("comp1").variable("apgrp2fvar6").selection().geom("geom1", 2);
    model.component("comp1").variable("apgrp2fvar6").selection().named("geom1_dfnpi2_dfnpart2wp6_bnd");

    model.param().set("dfn_d_f", "1[mm]");
    model.param().set("dfn_d_f_min", "0.1[mm]");
    model.param().set("dfn_d_f_max", "10[mm]");

    model.component("comp1").variable("apgrp2fvar6").set("df", 0.0031809455942002356, "Aperture");

    model.param().remove("dfn_d_f");
    model.param().remove("dfn_d_f_max");
    model.param().remove("dfn_d_f_min");

    model.component("comp1").physics("dl").create("dfn2", "Fracture", 2);
    model.component("comp1").physics("dl").feature("dfn2").selection().named("geom1_dfnpi2_csel1_bnd");
    model.component("comp1").physics("dl").feature("dfn2").set("df", "df");
    model.component("comp1").physics("dl").feature("dfn2").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("dfn2").feature("pm1").set("epsilon", "0.7");
    model.component("comp1").physics("dl").feature("dfn2").feature("pm1").set("permeabilityModelType", "cubicLaw");
    model.component("comp1").physics("dl").feature("dfn2").feature("pm1").set("ff", "1");

    model.component("comp1").geom("geom1").feature("dfnpi1").active(false);
    model.component("comp1").geom("geom1").feature("dfnpar1").active(false);
    model.component("comp1").geom("geom1").feature("dfnpi2").active(false);
    model.component("comp1").geom("geom1").feature("dfnpar2").active(false);

    model.component("comp1").mesh().create("tempmesh1");
    model.component("comp1").mesh("tempmesh1").run();

    model.result().dataset().create("tempdset1", "Mesh");
    model.result().dataset("tempdset1").set("mesh", "tempmesh1");
    model.result().numerical().create("tempmin1", "MinVolume");
    model.result().numerical("tempmin1").set("data", "tempdset1");
    model.result().numerical().create("tempmax1", "MaxVolume");
    model.result().numerical("tempmax1").set("data", "tempdset1");
    model.result().numerical("tempmin1").selection().named("geom1_sel2");
    model.result().numerical("tempmax1").selection().named("geom1_sel2");
    model.result().numerical("tempmin1").setIndex("expr", "x", 0);
    model.result().numerical("tempmin1").setIndex("expr", "y", 1);
    model.result().numerical("tempmin1").setIndex("expr", "z", 2);
    model.result().numerical("tempmax1").setIndex("expr", "x", 0);
    model.result().numerical("tempmax1").setIndex("expr", "y", 1);
    model.result().numerical("tempmax1").setIndex("expr", "z", 2);
    model.result().numerical("tempmin1").computeResult();
    model.result().numerical("tempmax1").computeResult();

    model.component("comp1").geom("geom1").feature("dfnpi1").active(true);
    model.component("comp1").geom("geom1").feature("dfnpar1").active(true);
    model.component("comp1").geom("geom1").feature("dfnpi2").active(true);
    model.component("comp1").geom("geom1").feature("dfnpar2").active(true);

    model.component("comp1").mesh().remove("tempmesh1");

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"dfnpi1_csel1", "dfnpi2_csel1"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6240\u6709\u88c2\u9699");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").run("unisel1");

    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat1").label("Water, liquid");
    model.material("mat1").set("family", "water");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("P0_amb", "550[mm/a]");

    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("gr1").set("useRref", true);
    model.component("comp1").physics("dl").feature("gr1").set("rref", new int[]{0, 0, -300});

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").label("\u5f3a\u6e17\u900f\u6027");
    model.component("comp1").material("pmat1").set("porosity", "0.25");
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1000[mD]"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material().create("pmat2", "PorousMedia");
    model.component("comp1").material("pmat2").selection().set(2);
    model.component("comp1").material("pmat2").selection().named("geom1_sel2");
    model.component("comp1").material("pmat2").label("\u5f31\u6e17\u900f\u6027");
    model.component("comp1").material("pmat2").set("porosity", "0.1");
    model.component("comp1").material("pmat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"20[mD]"});
    model.component("comp1").material("pmat2").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk1").selection().named("geom1_unisel1");

    model.component("comp1").physics("dl").create("prec1", "Precipitation", 2);
    model.component("comp1").physics("dl").feature("prec1").selection().set(10);
    model.component("comp1").physics("dl").feature("prec1").set("item.P0_src", "root.comp1.ampr1.P0_amb");
    model.component("comp1").physics("dl").feature("prec1").set("slopeCorrection", true);
    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 2);
    model.component("comp1").physics("dl").feature("hh1").selection().set(1, 2, 25, 80);
    model.component("comp1").physics("dl").feature("hh1").set("H0", "0.5[m/km]*x");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").selection().named("geom1_sel2");
    model.result().numerical("av1").setIndex("expr", "dl.U", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5e73\u5747 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical().create("av2", "AvSurface");
    model.result().numerical("av2").set("intvolume", true);
    model.result().numerical("av2").selection().named("geom1_unisel1");
    model.result().numerical("av2").setIndex("expr", "dl.U", 0);
    model.result().numerical("av2").set("table", "tbl1");
    model.result().numerical("av2").appendResult();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8fbe\u897f\u901f\u5ea6\u5927\u5c0f (m/s)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "dl.U");
    model.result("pg1").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "3e-3");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", "dl.U");
    model.result("pg1").feature("vol1").set("inheritplot", "surf1");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("geom1_sel3");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("sel1").selection().named("geom1_sel1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("tran1").set("transparency", 0.8);
    model.result("pg1").run();
    model.result("pg1").create("str1", "StreamlineSurface");
    model.result("pg1").feature("str1").selection().named("geom1_unisel1");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();

    model.title("\u88c2\u7f1d\u6027\u50a8\u5c42\u4e2d\u7684\u6d41\u52a8");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u901a\u8fc7\u88c2\u7f1d\u6027\u50a8\u5c42\u7684\u6d41\u52a8\uff0c\u5176\u4e2d\u5c06\u50a8\u5c42\u4f5c\u4e3a\u79bb\u6563\u88c2\u7f1d\u7f51\u7edc\u8fdb\u884c\u5efa\u6a21\uff0c\u88c2\u7f1d\u7684\u4f4d\u7f6e\u3001\u5927\u5c0f\u3001\u65b9\u5411\u548c\u5b54\u5f84\u4e3a\u968f\u673a\u5206\u5e03\u3002");

    model.label("fractured_reservoir_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
