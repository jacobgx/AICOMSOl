/*
 * pm_motor_2d_campbell_diagram_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class pm_motor_2d_campbell_diagram_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("Np", "10");
    model.param().descr("Np", "\u6781\u6570");
    model.param().set("Ns", "12");
    model.param().descr("Ns", "\u69fd\u6570");

    model.geom()
         .load(new String[]{"part1"}, "ACDC_Module\\Rotating_Machinery_2D\\Rotors\\Internal\\surface_mounted_magnet_internal_rotor_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "number_of_modeled_poles", "Np");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_rotor_magnets", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_all.dom", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.geom()
         .load(new String[]{"part2"}, "ACDC_Module\\Rotating_Machinery_2D\\Stators\\External\\slotted_external_stator_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "number_of_modeled_slots", "Ns");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "external_air_size", "1.5[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_outer_fillet_size", "0.4[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_inner_fillet_size", "0.4[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "slot_winding_type", 2);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_iron.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_stator_slots", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_all.dom", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{90, 75});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, 7.5});
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{5, 20});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{15, -25});
    model.component("comp1").geom("geom1").feature("r2").set("rot", 20);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "53/2");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("dif1", "r1");
    model.component("comp1").geom("geom1").feature("int1").set("keep", true);
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("dif1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("dif2");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("int1", "mir1");
    model.component("comp1").geom("geom1").feature("dif3").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u58f0\u5b66\u57df");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif3", 1, 2, 3, 4, 6, 7);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7ed3\u6784\u57df");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("int1", 1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("mir1", 1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("pi2", 2, 27);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7535\u78c1\u57df");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"pi1_all", "pi2_all"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u7a33\u6001\u57df");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif3", "int1", "mir1", "pi2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("pm_motor_2d_campbell_diagram_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
